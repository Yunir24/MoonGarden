package com.dauto.data.storage.model

import androidx.room.*

@Dao
interface WeatherDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWeatherDay(weatherDay: WeatherDay)

    @Query("SELECT * FROM weather_day WHERE id = 0" )
    suspend fun getWeatherDay(): WeatherDay

}