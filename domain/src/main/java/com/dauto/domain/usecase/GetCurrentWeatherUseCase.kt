package com.dauto.domain.usecase

import com.dauto.domain.MoonCalendarRepository
import com.dauto.domain.weatherentity.WeatherItem

class GetCurrentWeatherUseCase(private val moonCalendarRepository: MoonCalendarRepository) {

    suspend operator fun invoke(): WeatherItem {
        return moonCalendarRepository.getCurrentWeather()
    }
}
