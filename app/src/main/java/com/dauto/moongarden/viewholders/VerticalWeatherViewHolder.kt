package com.dauto.moongarden.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.dauto.domain.weatherentity.Day
import com.dauto.moongarden.databinding.WeatherItemVericalBinding

class VerticalWeatherViewHolder(private val verticalWeatherBinding: WeatherItemVericalBinding)
    : RecyclerView.ViewHolder(verticalWeatherBinding.root){


        fun bind(day: Day){
            verticalWeatherBinding.textView.text=day.date
        }
}