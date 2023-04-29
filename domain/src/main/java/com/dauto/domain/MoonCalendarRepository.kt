package com.dauto.domain

import com.dauto.domain.moonentity.MonthAndDays
import com.dauto.domain.moonentity.MoonDay
import com.dauto.domain.weatherentity.WeatherItem

interface MoonCalendarRepository {


    suspend fun getMoonDay(dayId: String): MoonDay

    suspend fun getMoonMonth(monthId: String): MonthAndDays

    suspend fun getMoonDayList(month: String): List<MoonDay>

    suspend fun getCurrentWeather(): WeatherItem




}