package com.dauto.domain.usecase

import com.dauto.domain.MoonCalendarRepository
import com.dauto.domain.weatherentity.CurrentWeather
import kotlinx.coroutines.flow.Flow

class GetCurrentWeatherUseCase(private val moonCalendarRepository: MoonCalendarRepository) {

    operator fun invoke(): Flow<CurrentWeather>{
        return moonCalendarRepository.getCurrentWeather()
    }
}
