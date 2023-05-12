package com.dauto.data.storage.model.weatherDay

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "hours_table",
    indices = [Index("day_id")]
)
data class HoursDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "day_id")
    val dayId: String,

    @ColumnInfo(name = "hour")
    val time: String,

    @ColumnInfo(name = "temperature")
    val temperature: String,

    @ColumnInfo(name = "icon")
    val conditionIcon : String
)
