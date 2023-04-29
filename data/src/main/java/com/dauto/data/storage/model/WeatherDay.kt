package com.dauto.data.storage.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_day")
data class WeatherDay (
    @PrimaryKey()
    val id:Int = 0,
    val lastUpdate: String,

    val temperature: String,

    @Embedded
    val condition: ConditionDbModel,

    val wind:Int,

    val humidity: Int,

    val cloud: Int,

    val feelsLike: Int,
        )