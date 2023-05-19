package com.dauto.domain.usecase

import com.dauto.domain.MoonCalendarRepository
import com.dauto.domain.weatherentity.WeatherDayWithHours
import kotlinx.coroutines.flow.Flow

class GetWeatherDayListUseCase (private val moonCalendarRepository: MoonCalendarRepository) {

    operator fun invoke(): Flow<List<WeatherDayWithHours>> {
        return moonCalendarRepository.getWeatherList()
    }
}