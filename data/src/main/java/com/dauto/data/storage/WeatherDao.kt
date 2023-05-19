package com.dauto.data.storage

import androidx.room.*
import com.dauto.data.storage.model.weatherDay.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {


    @Transaction
    suspend fun insertWeatherItem(
        currentWeatherDbModel: CurrentWeatherDbModel,
        dayDbModel: List<WeatherDayDbModel>,
        hoursDbModel: List<HoursDbModel>
    ){
        addCurrentWeather(currentWeatherDbModel)
        addDayList(dayDbModel)
        addHourList(hoursDbModel)
    }

    @Transaction
    suspend fun addCurrentWeather(currentWeatherDbModel: CurrentWeatherDbModel){
        deleteCurrentTable()
        setCurrentWeather(currentWeatherDbModel)
    }
    @Transaction
    suspend fun addDayList(dayDbModel: List<WeatherDayDbModel>){
        deleteDaysTable()
        setDayList(dayDbModel)
    }
    @Transaction
    suspend fun addHourList(hoursDbModel: List<HoursDbModel>){
        deleteHoursTable()
        setHoursList(hoursDbModel)
    }


    @Insert(entity = HoursDbModel::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun setHoursList(hoursDbModel: List<HoursDbModel>)

    @Insert(entity = WeatherDayDbModel::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun setDayList(dayDbModel: List<WeatherDayDbModel>)


    @Insert(entity = CurrentWeatherDbModel::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCurrentWeather(currentWeatherDbModel: CurrentWeatherDbModel)

    @Transaction
    @Query(
        "SELECT * FROM weather_day"
    )
    fun getForecastWeather(): Flow<List<DayWithHoursDbModel>>

    @Query("SELECT * FROM current_weather WHERE id = 132")
    fun getCurrentWeather(): Flow<CurrentWeatherDbModel>


    @Query("DELETE FROM hours_table")
    suspend fun deleteHoursTable()

    @Query("DELETE FROM weather_day")
    suspend fun deleteDaysTable()

    @Query("DELETE FROM current_weather")
    suspend fun deleteCurrentTable()

    @Transaction
    suspend fun destroyAll() {
        deleteDaysTable()
        deleteCurrentTable()
        deleteHoursTable()
    }

}