package com.dauto.moongarden.state

import com.dauto.domain.weatherentity.CurrentWeather

sealed class CurrentWeatherState {

    object Error : CurrentWeatherState()
    object Loading : CurrentWeatherState()
    class Result(val currentWeather: CurrentWeather) : CurrentWeatherState()
}