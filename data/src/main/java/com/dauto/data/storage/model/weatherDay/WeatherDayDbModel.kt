package com.dauto.data.storage.model.weatherDay

import androidx.room.*

@Entity(tableName = "weather_day"
        )
data class WeatherDayDbModel(

    @PrimaryKey
    val date: String,

    @ColumnInfo(name = "max_temp")
    val maxTemperature: String,

    @ColumnInfo(name = "min_temp")
    val minTemperature: String,

    @ColumnInfo(name = "rain_chance")
    val chanceRain: Int,

    @Embedded
    val condition: ConditionDbModel,

    @Embedded
    val astro: AstroDbModel,


)
//,
//indices = [Index("current_id")]
//@ColumnInfo(name = "current_id")
//val currentId: Int,
