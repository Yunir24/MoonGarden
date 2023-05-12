package com.dauto.data.storage.model.moonCalendar

import androidx.room.ColumnInfo

data class FlowersDbModel(

    @ColumnInfo("annuals")
    var annuals: String?,

    @ColumnInfo("twoyear_longterm")
    var twoyearAndLongterm: String?,

    @ColumnInfo("bulbous_and_tuberous")
    var bulbousAndTuberous: String?,

    )