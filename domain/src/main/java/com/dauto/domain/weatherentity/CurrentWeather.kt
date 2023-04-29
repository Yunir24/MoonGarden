package com.dauto.domain.weatherentity

data class CurrentWeather(

    val lastUpdate: String,

    val temperature: String,

    val condition: Condition,

    val wind: Int,

    val humidity: Int,

    val cloud: Int,

    val feelsLike: Int,
)
