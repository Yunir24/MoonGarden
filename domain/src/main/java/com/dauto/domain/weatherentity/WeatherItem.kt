package com.dauto.domain.weatherentity

data class WeatherItem(

    val location: Location,
    val current: CurrentWeather,
    val forecast: Forecast
)
