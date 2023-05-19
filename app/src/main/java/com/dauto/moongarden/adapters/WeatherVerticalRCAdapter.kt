package com.dauto.moongarden.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dauto.domain.weatherentity.WeatherDayWithHours
import com.dauto.moongarden.R
import com.dauto.moongarden.databinding.WeatherItemVericalBinding
import java.text.SimpleDateFormat
import java.util.*

class WeatherVerticalRCAdapter : ListAdapter<WeatherDayWithHours,
        WeatherVerticalRCAdapter.VerticalWeatherViewHolder>
    (WeatherDayDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalWeatherViewHolder {
        val binding = WeatherItemVericalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VerticalWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VerticalWeatherViewHolder, position: Int) {
        val weatherItem = getItem(position)
        holder.bindViews(weatherItem, position)
    }


    private object WeatherDayDiffCallback : DiffUtil.ItemCallback<WeatherDayWithHours>() {
        override fun areItemsTheSame(
            oldItem: WeatherDayWithHours,
            newItem: WeatherDayWithHours
        ): Boolean {
            return oldItem.day.date == newItem.day.date

        }

        override fun areContentsTheSame(
            oldItem: WeatherDayWithHours,
            newItem: WeatherDayWithHours
        ): Boolean {
            return oldItem == newItem
        }

    }

    class VerticalWeatherViewHolder(private val binding: WeatherItemVericalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var horizontalRCAdapter: WeatherHorizontalRCAdapter

        companion object{
            private const val PREFIX = "https:"
        }

        fun bindViews(weatherItem: WeatherDayWithHours, position: Int) {

            with(binding) {
                currentTempTV.text = weatherItem.day.avgTemperature
                chanceRainTV.text = weatherItem.day.chanceRain
                windTV.text = weatherItem.day.wind
                humidityTV.text = weatherItem.day.humidity
                currentDateTV.text = weatherItem.day.date
                sunriseTV.text = weatherItem.day.astro.sunrise
                sunsetTv.text = weatherItem.day.astro.sunset
                Glide.with(root.context)
                    .load(PREFIX + weatherItem.day.condition.icon)
                    .error(R.drawable.ic_baseline_image_not_supported_24)
                    .placeholder(R.drawable.air_20px)
                    .into(binding.imageView)
                horizontalRCAdapter = WeatherHorizontalRCAdapter(root.context)
                horizontalRCView.adapter = horizontalRCAdapter
                if (position == 0) {
                    val time = getCurrentHour()
                    val list = weatherItem.hoursList
                    val end= list.size
                    horizontalRCAdapter.submitList(list.subList(time,end))
                } else {
                    horizontalRCAdapter.submitList(weatherItem.hoursList)
                }

            }
        }

        private fun getCurrentHour(): Int {
            val forDay = SimpleDateFormat(/* pattern = */ "HH", Locale.US)
            return try {
                forDay.format(Date()).toInt()
            } catch (e: Exception) {
                0
            }
        }
    }


}