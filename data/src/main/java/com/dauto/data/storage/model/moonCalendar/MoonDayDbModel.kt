package com.dauto.data.storage.model.moonCalendar

import androidx.room.*


@Entity(tableName = "moonday_table",
     indices = [Index("month")]
)
data class MoonDayDbModel(

    @PrimaryKey
    @ColumnInfo("day_id")
    var day: String,
    @ColumnInfo(name = "month")
    val month: String?,
    @ColumnInfo("moon_phase")
    val moonPhase: String?,
    @ColumnInfo("moon_info")
    val moonInfo: String?,
    @ColumnInfo("backyard")
    val backyard: String?,
    @ColumnInfo("window_sill")
    val windowSill: String?,
    @ColumnInfo("green_houses")
    val greenHouses: String?,
    @ColumnInfo("flower_grower")
    val flowerGrower: String?,
    @ColumnInfo("not_recommended")
    val notRecommended: String?,
    @ColumnInfo("in_the_garden")
    val inTheGarden: String?,
    @ColumnInfo("harvesting")
    val harvesting: String?,
) {

    operator fun iterator(): List<Pair<String, String?>> {
        return listOf(
            "backyard" to backyard?.trim(),
            "sill" to windowSill?.trim(),
            "green" to greenHouses?.trim(),
            "flower" to flowerGrower?.trim(),
            "notRec" to notRecommended?.trim(),
            "inGar" to inTheGarden?.trim(),
            "harv" to harvesting?.trim(),
        )
    }}