package com.dauto.data.storage.model

import androidx.room.Embedded
import androidx.room.Relation

data class MonthAndDayModel(

    @Embedded
    val month: MoonMonthDbModel,

    @Relation(parentColumn = "month_id", entityColumn = "month")
    var daysList: List<MoonDayDbModel>
)