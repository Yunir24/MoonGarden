package com.dauto.domain.weatherentity

data class CurrentWeather(

    val location: String,

    val lastUpdate: String,

    val temperature: String,

    val condition: Condition,

    val wind: String,

    val humidity: String,

    val cloud: String,

    val feelsLike: String,
)
