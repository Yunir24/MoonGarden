package com.dauto.data.storage.model.moonCalendar

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "month_table")
data class MoonMonthDbModel(

    @PrimaryKey
    @ColumnInfo("month_id")
    var name: String,

    @ColumnInfo("description")
    var description: String?,

    @Embedded
    var dayGoodBad: DayGoodBadDbModel?,

    @Embedded
    var daysForSowing: DayForSowingDbModel?,

    @Embedded
    var flowers: FlowersDbModel?,

    @Embedded
    var seedlings: SeedlingsDbModel?,


    )