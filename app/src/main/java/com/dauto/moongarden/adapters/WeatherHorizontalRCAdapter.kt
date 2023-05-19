package com.dauto.moongarden.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dauto.domain.weatherentity.Hour
import com.dauto.moongarden.R
import com.dauto.moongarden.databinding.WeatherItemHorizontalBinding

class WeatherHorizontalRCAdapter(private val context: Context)
    : ListAdapter<Hour,
        WeatherHorizontalRCAdapter.HorizontalWeatherViewHolder>(WeatherHourDiffCallback){




    private object WeatherHourDiffCallback : DiffUtil.ItemCallback<Hour>(){
        override fun areItemsTheSame(oldItem: Hour, newItem: Hour): Boolean {
            return oldItem.time == newItem.time
        }

        override fun areContentsTheSame(oldItem: Hour, newItem: Hour): Boolean {
            return  oldItem==newItem
        }

    }

    class HorizontalWeatherViewHolder(private val binding: WeatherItemHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindViews(hour: Hour, context: Context) {
            val suffics = "https:"
            with(binding) {
                hourItemTextView.text = hour.time
                temperatureItemTextView.text = hour.temperature
                Glide.with(context)
                    .load(suffics + hour.conditionIcon)
                    .error(R.drawable.ic_baseline_image_not_supported_24)
                    .placeholder(R.drawable.air_20px)
                    .into(binding.imageViewIconHorizontal)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalWeatherViewHolder {
        val binding = WeatherItemHorizontalBinding.inflate(
        LayoutInflater.from(parent.context), parent , false)
        return HorizontalWeatherViewHolder(binding)

    }

    override fun onBindViewHolder(holder: HorizontalWeatherViewHolder, position: Int) {
        holder.bindViews(getItem(position), context)
    }
}