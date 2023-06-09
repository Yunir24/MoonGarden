package com.dauto.domain.usecase

import com.dauto.domain.MoonCalendarRepository

class UpdateCurrentWeatherUseCase(private val moonCalendarRepository: MoonCalendarRepository) {

    suspend operator fun invoke(location: String) {
        moonCalendarRepository.updateCurrentWeather(location)
    }
}