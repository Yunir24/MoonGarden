package com.dauto.domain

import com.dauto.domain.moonentity.MonthAndDays
import com.dauto.domain.moonentity.MoonDay
import com.dauto.domain.weatherentity.CurrentWeather
import com.dauto.domain.weatherentity.WeatherDayWithHours
import kotlinx.coroutines.flow.Flow

interface MoonCalendarRepository {


    suspend fun getMoonDay(dayId: String): MoonDay

    suspend fun getMoonMonth(monthId: String): MonthAndDays

    suspend fun getMoonDayList(month: String): List<MoonDay>

    fun getCurrentWeather(): Flow<CurrentWeather>

    fun getWeatherList(): Flow<List<WeatherDayWithHours>>

    suspend fun updateCurrentWeather(location: String)


}