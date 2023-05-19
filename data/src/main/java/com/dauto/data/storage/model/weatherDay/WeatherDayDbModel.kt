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

    @ColumnInfo(name = "snow_chance")
    val chanceSnow: Int,

    @ColumnInfo(name = "wind")
    val wind: String,

    @ColumnInfo(name = "humidity")
    val humidity: String,

    @Embedded
    val condition: ConditionDbModel,

    @Embedded
    val astro: AstroDbModel,


)
//,
//indices = [Index("current_id")]
//@ColumnInfo(name = "current_id")
//val currentId: Int,
