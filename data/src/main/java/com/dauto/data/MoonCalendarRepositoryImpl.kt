package com.dauto.data

import android.app.Application
import com.dauto.data.network.ApiFactory
import com.dauto.data.storage.AppDatabase
import com.dauto.domain.MoonCalendarRepository
import com.dauto.domain.moonentity.MonthAndDays
import com.dauto.domain.moonentity.MoonDay
import com.dauto.domain.weatherentity.WeatherItem

class MoonCalendarRepositoryImpl(application: Application) : MoonCalendarRepository {


    private val querry = "53.334301, 58.518090"
    private val dayz = "5"
    private val lang = "ru"

    private val calendarDao = AppDatabase.getInstance(application).dao()
    private val weatherDao = AppDatabase.getInstance(application).weatherDao()
    private val apiService = ApiFactory.apiService

    override suspend fun getMoonDay(dayId: String): MoonDay {
        return calendarDao.getMoonDay(dayId).toEntity()
    }

    override suspend fun getMoonMonth(monthId: String): MonthAndDays {
        val monthModel = calendarDao.getMoonMonth(monthId)
        return MonthAndDays(
            month = monthModel.month.toEntity(),
            daysList = monthModel.daysList.toEntity()
        )
    }

    override suspend fun getMoonDayList(month: String): List<MoonDay> {
        return calendarDao.getMoonDaylist(month).toEntity()

    }

    override suspend fun getCurrentWeather(): WeatherItem {
            val weather = apiService.getCurrentWeather(querry, lang, tokken, dayz)
            weatherDao.addWeatherDay(weather.toDbModel())
            return weather.toEntity()
    }




}