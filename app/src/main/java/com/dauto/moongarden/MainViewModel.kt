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
import com.dauto.domain.weatherentity.WeatherItem
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val calendarRepository = MoonCalendarRepositoryImpl(application)

    private val getMoonDayUseCase = GetMoonDayUseCase(calendarRepository)
    private val getMoonDayListUseCase = GetMoonDayListUseCase(calendarRepository)
    private val getMoonMonthUseCase = GetMoonMonthUseCase(calendarRepository)
    private val getWeatherUseCase = GetCurrentWeatherUseCase(calendarRepository)

    private var _moonDay = MutableLiveData<MoonDay>()
    val moonDay: LiveData<MoonDay>
        get() = _moonDay
    private var _moonMonth = MutableLiveData<MonthAndDays>()
    val moonMonth: LiveData<MonthAndDays>
        get() = _moonMonth
    private var _weather = MutableLiveData<WeatherItem>()
    val weather: LiveData<WeatherItem>
        get() = _weather


    init {
        Log.d("viewCheck", "i'm alive")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("viewCheck", "i'm dead")
    }

    fun getWeather(){
        viewModelScope.launch {
          _weather.value =  getWeatherUseCase.invoke()
        }
    }

    fun getMoonDay(dayId: String) {
        viewModelScope.launch{
            val day = getMoonDayUseCase.invoke(dayId)
            _moonDay.value = day
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
        return forDay.format(Date(23,1,1))
    }

    fun getCurrentMonth(): String {
        val forMonth = SimpleDateFormat(/* pattern = */ "MMMM ", Locale.US)
        return forMonth.format(Date(23,0,1))
    }

}