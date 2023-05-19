package com.dauto.moongarden

import android.app.Application
import androidx.lifecycle.*
import com.dauto.data.MoonCalendarRepositoryImpl
import com.dauto.domain.moonentity.MonthAndDays
import com.dauto.domain.moonentity.MoonDay
import com.dauto.domain.usecase.*
import com.dauto.domain.weatherentity.WeatherDayWithHours
import com.dauto.moongarden.state.CurrentWeatherState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val calendarRepository = MoonCalendarRepositoryImpl(application)

    private val getMoonDayUseCase = GetMoonDayUseCase(calendarRepository)
    private val getMoonMonthUseCase = GetMoonMonthUseCase(calendarRepository)

    private val getWeatherUseCase = GetCurrentWeatherUseCase(calendarRepository)
    private val getWeatherDayListUseCase = GetWeatherDayListUseCase(calendarRepository)
    private val updateCurrentWeatherUseCase = UpdateCurrentWeatherUseCase(calendarRepository)

    private var _moonDay = MutableLiveData<MoonDay>()
    val moonDay: LiveData<MoonDay>
        get() = _moonDay

    private var _moonMonth = MutableLiveData<MonthAndDays>()
    val moonMonth: LiveData<MonthAndDays>
        get() = _moonMonth

    private val weatherLoading = MutableSharedFlow<CurrentWeatherState>()



    val currentWeather: LiveData<CurrentWeatherState> = getWeatherUseCase.invoke()
        .map { CurrentWeatherState.Result(it) as CurrentWeatherState }
        .catch {
            emit(CurrentWeatherState.Error)
        }
        .onStart {
            emit(CurrentWeatherState.Loading)
        }
        .mergeWith(weatherLoading).asLiveData(Dispatchers.IO)


    val forecastWeatherList: LiveData<List<WeatherDayWithHours>> = getWeatherDayListUseCase.invoke()
        .filter { it.isNotEmpty() }
        .asLiveData()


    fun getMoonDay() {
        viewModelScope.launch {
            val day = getMoonDayUseCase.invoke(getCurrentDay())
            _moonDay.value = day
        }
    }


    fun updateCurrentWeather(location: String) {
        viewModelScope.launch {
            weatherLoading.emit(CurrentWeatherState.Loading)
            updateCurrentWeatherUseCase.invoke(location)
        }
    }

    fun getMonthInfo() {
        viewModelScope.launch {
            val month = getMoonMonthUseCase.invoke(getCurrentMonth())
            _moonMonth.value = month
        }
    }


    private fun <T> Flow<T>.mergeWith(another: Flow<T>): Flow<T> {
        return merge(this, another)
    }

    private fun getCurrentDay(): String {
        val forDay = SimpleDateFormat(/* pattern = */ "dd-MM-yy", Locale.US)
        return forDay.format(Date())
    }

    private fun getCurrentMonth(): String {
        val forMonth = SimpleDateFormat(/* pattern = */ "MMMM", Locale.US)
        return forMonth.format(Date())
    }

}