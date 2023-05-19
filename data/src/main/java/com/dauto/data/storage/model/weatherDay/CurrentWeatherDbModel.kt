package com.dauto.data.storage.model.weatherDay

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
data class CurrentWeatherDbModel (
    @PrimaryKey()
    val id:Int = 0,
    val location: String,
    val lastUpdate: String,

    val temperature: String,

    @Embedded
    val condition: ConditionDbModel,

    val wind:Int,

    val humidity: Int,

    val cloud: Int,

    val feelsLike: String,
        )