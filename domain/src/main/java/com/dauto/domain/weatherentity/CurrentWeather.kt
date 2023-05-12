package com.dauto.domain.weatherentity

data class CurrentWeather(

    val location: String,

    val lastUpdate: String,

    val temperature: String,

    val condition: Condition,

    val wind: Int,

    val humidity: Int,

    val cloud: Int,

    val feelsLike: Int,
)
