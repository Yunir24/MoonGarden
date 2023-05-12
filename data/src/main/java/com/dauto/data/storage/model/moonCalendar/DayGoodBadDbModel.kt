package com.dauto.data.storage.model.moonCalendar

import androidx.room.ColumnInfo

data class DayGoodBadDbModel(

    @ColumnInfo(name = "favorable")
    val favorable: String?,
    @ColumnInfo(name = "unfavorable")
    val unfavorable: String?,
)