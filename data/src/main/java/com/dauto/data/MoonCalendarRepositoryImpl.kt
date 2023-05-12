package com.dauto.data

import android.app.Application
import android.util.Log
import com.dauto.data.network.ApiFactory
import com.dauto.data.storage.AppDatabase
import com.dauto.domain.MoonCalendarRepository
import com.dauto.domain.moonentity.MonthAndDays
import com.dauto.domain.moonentity.MoonDay
import com.dauto.domain.weatherentity.CurrentWeather
import com.dauto.domain.weatherentity.WeatherDayWithHours

class MoonCalendarRepositoryImpl(application: Application) : MoonCalendarRepository {

    private val tokken =  BuildConfig.TOKEN
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

    override suspend fun getCurrentWeather(): CurrentWeather {
//        uploadWeather() 35/34
        return weatherDao.getCurrentWeather().toEntity()

    }

    override suspend fun getWeatherList(): List<WeatherDayWithHours> {
        return mapDayListDbModelToEntity(weatherDao.getForecastWeather())
    }


    override suspend fun updateCurrentWeather() {
        uploadWeather()
    }


    private suspend fun uploadWeather() {

        try {
            val weather = apiService.getCurrentWeather(querry, lang, tokken, dayz)
            val current = convertWeatherDtoToDbModel(weather)
            val daylist = mapDtoDayListToDbModelList(weather)
            val hourList = mapDtoHoursListToDbModelList(weather)
            weatherDao.insertWeatherItem(current, daylist, hourList)
        } catch (e: Exception) {
            Log.d("DebugApp", e.toString())
        }

    }


}