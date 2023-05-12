package com.dauto.moongarden

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dauto.data.MoonCalendarRepositoryImpl
import com.dauto.domain.moonentity.MonthAndDays
import com.dauto.domain.moonentity.MoonDay
import com.dauto.domain.usecase.*
import com.dauto.domain.weatherentity.CurrentWeather
import com.dauto.domain.weatherentity.WeatherDayWithHours
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val calendarRepository = MoonCalendarRepositoryImpl(application)

    private val getMoonDayUseCase = GetMoonDayUseCase(calendarRepository)
    private val getMoonDayListUseCase = GetMoonDayListUseCase(calendarRepository)
    private val getMoonMonthUseCase = GetMoonMonthUseCase(calendarRepository)

    private val getWeatherUseCase = GetCurrentWeatherUseCase(calendarRepository)
    private val getWeatherDayListUseCase = GetWeatherDayListUseCase(calendarRepository)
    private val updateCurrentWeatherUseCase= UpdateCurrentWeatherUseCase(calendarRepository)

    private var _moonDay = MutableLiveData<MoonDay>()
    val moonDay: LiveData<MoonDay>
        get() = _moonDay
    private var _moonMonth = MutableLiveData<MonthAndDays>()
    val moonMonth: LiveData<MonthAndDays>
        get() = _moonMonth
    private var _weatherCurrent = MutableLiveData<CurrentWeather>()
    val currentWeather: LiveData<CurrentWeather>
        get() = _weatherCurrent
    private var _forecastWeatherList = MutableLiveData<List<WeatherDayWithHours>>()
    val forecastWeatherList: LiveData<List<WeatherDayWithHours>>
        get() = _forecastWeatherList


    init {
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("viewCheck", "i'm dead")
    }

    fun getWeather(){
        viewModelScope.launch {
            _weatherCurrent.value= getWeatherUseCase.invoke()
        }
    }

    fun getMoonDay(dayId: String) {
        viewModelScope.launch{
            val day = getMoonDayUseCase.invoke(getCurrentDay())
            _moonDay.value = day
        }
    }


    fun getForecastList(){

    }

    fun updateCurrentWeather(){
        viewModelScope.launch{
            updateCurrentWeatherUseCase.invoke()
            delay(2000)
            getWeather()
        }
    }

    fun getMonthInfo(monthId: String){
        viewModelScope.launch {
            val month = getMoonMonthUseCase.invoke(monthId)
            _moonMonth.value = month
        }
    }


    fun getCurrentDay(): String {
        val forDay = SimpleDateFormat(/* pattern = */ "dd-MM-yy", Locale.US)
        return forDay.format(Date())
    }

    fun getCurrentMonth(): String {
        val forMonth = SimpleDateFormat(/* pattern = */ "MMMM ", Locale.US)
        return forMonth.format(Date())
    }

}