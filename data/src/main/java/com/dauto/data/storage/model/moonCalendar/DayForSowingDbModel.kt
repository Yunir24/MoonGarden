package com.dauto.data.storage.model.moonCalendar

import androidx.room.ColumnInfo

data class DayForSowingDbModel(

    @ColumnInfo("cucumbers")
    var cucumbers: String?,

    @ColumnInfo("pepper_eggplant")
    var pepperEggplant: String?,

    @ColumnInfo("cabbage")
    var cabbage: String?,

    @ColumnInfo("garlic")
    var garlic: String?,

    @ColumnInfo("root_vegetables")
    var rootVegetables: String?,

    @ColumnInfo("tomato")
    var tomato: String?,

    @ColumnInfo("onion")
    var onion: String?,

    @ColumnInfo("different_greens")
    var differentGreens: String?,
)