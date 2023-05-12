package com.dauto.domain.usecase

import com.dauto.domain.MoonCalendarRepository
import com.dauto.domain.weatherentity.CurrentWeather

class GetCurrentWeatherUseCase(private val moonCalendarRepository: MoonCalendarRepository) {

    suspend operator fun invoke(): CurrentWeather{
        return moonCalendarRepository.getCurrentWeather()
    }
}
