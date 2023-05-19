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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoonCalendarRepositoryImpl(application: Application) : MoonCalendarRepository {


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

    override fun getCurrentWeather(): Flow<CurrentWeather> {
        return weatherDao.getCurrentWeather()
            .map{ it.toEntity()}
    }

    override  fun getWeatherList(): Flow<List<WeatherDayWithHours>> {
        return weatherDao.getForecastWeather().map {
            mapDayListDbModelToEntity(it)
        }
    }


    override suspend fun updateCurrentWeather(location: String) {
        uploadWeather(location)
    }


    private suspend fun uploadWeather(location: String) {

        try {
            val weather = apiService.getCurrentWeather(location, LANG, TOKEN, DAYCOUNT)
            val current = convertWeatherDtoToDbModel(weather)
            val daylist = mapDtoDayListToDbModelList(weather)
            val hourList = mapDtoHoursListToDbModelList(weather)
            weatherDao.insertWeatherItem(current, daylist, hourList)
        } catch (e: Exception) {
            Log.d("DebugApp", e.toString())
        }

    }


}