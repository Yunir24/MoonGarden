package com.dauto.data.storage.model.moonCalendar

import androidx.room.ColumnInfo

data class SeedlingsDbModel(

    @ColumnInfo("is_empty_seedlings")
    var isEmpty: Int,

    @ColumnInfo("fruit_trees")
    var fruitTrees: String?,

    @ColumnInfo("grape")
    var grape: String?,

    @ColumnInfo("gooseberry")
    var gooseberry: String?,

    @ColumnInfo("raspberry")
    var raspberry: String?,

    @ColumnInfo("strawberry")
    var strawberry: String?,

    @ColumnInfo("rooting_digging")
    var rooting_digging: String?,

    @ColumnInfo("grafting")
    var grafting: String?,
)