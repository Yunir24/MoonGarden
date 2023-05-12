package com.dauto.domain.usecase

import com.dauto.domain.MoonCalendarRepository
import com.dauto.domain.weatherentity.WeatherDayWithHours

class GetWeatherDayListUseCase (private val moonCalendarRepository: MoonCalendarRepository) {

    suspend operator fun invoke(): List<WeatherDayWithHours> {
        return moonCalendarRepository.getWeatherList()
    }
}