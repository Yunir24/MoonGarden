package com.dauto.data.storage.model.weatherDay

import androidx.room.Embedded
import androidx.room.Relation

data class DayWithHoursDbModel(

    @Embedded
    val weatherDayDbModel: WeatherDayDbModel,

    @Relation(parentColumn = "date", entityColumn = "day_id")
    val hoursList: List<HoursDbModel>

)