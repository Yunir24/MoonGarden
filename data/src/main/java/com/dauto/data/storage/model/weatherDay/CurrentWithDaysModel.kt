package com.dauto.data.storage.model.weatherDay

import androidx.room.Embedded
import androidx.room.Relation

data class CurrentWithDaysModel(

    @Embedded
    val currentWeatherDbModel: CurrentWeatherDbModel,
//
//    @Relation(parentColumn = "id", entityColumn = "current_id")
//    val weatherDayList: List<DayWithHoursModel>

)
