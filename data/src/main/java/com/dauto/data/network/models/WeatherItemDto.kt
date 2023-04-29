package com.dauto.data.network.models

data class WeatherItemDto(

    val location: LocationDto,
    val current: CurrentWeatherDto,
    val forecast: ForecastDto
)
