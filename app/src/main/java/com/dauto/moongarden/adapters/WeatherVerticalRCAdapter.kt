package com.dauto.moongarden.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dauto.domain.weatherentity.Day
import com.dauto.moongarden.databinding.WeatherItemVericalBinding
import com.dauto.moongarden.viewholders.VerticalWeatherViewHolder

class WeatherVerticalRCAdapter() : RecyclerView.Adapter<VerticalWeatherViewHolder>(){
    val list = listOf<Day>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalWeatherViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = WeatherItemVericalBinding.inflate(from,parent,false)
        return VerticalWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VerticalWeatherViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int=list.size
}