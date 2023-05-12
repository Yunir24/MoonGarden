package com.dauto.data.storage

import androidx.room.*
import com.dauto.data.storage.model.moonCalendar.MonthAndDayModel
import com.dauto.data.storage.model.moonCalendar.MoonDayDbModel
import com.dauto.data.storage.model.moonCalendar.MoonMonthDbModel

@Dao
interface MoonСalendarDao {


    @Query(
        "SELECT * FROM moonday_table WHERE day_id = :dayId"
    )
    suspend fun getMoonDay(dayId: String): MoonDayDbModel


    @Transaction
    @Query(
        "SELECT * FROM month_table WHERE month_id = :monthId"
    )
    suspend fun getMoonMonth(monthId: String): MonthAndDayModel

    @Query(
        "SELECT * FROM moonday_table WHERE month = :monthId"
    )
    suspend fun getMoonDaylist(monthId: String): List<MoonDayDbModel>

    @Insert(entity = MoonDayDbModel::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun setDAys(moonDayDbModel: List<MoonDayDbModel>)

    @Insert(entity = MoonMonthDbModel::class,onConflict = OnConflictStrategy.REPLACE)
    suspend fun setMonth(monthDbModel: MoonMonthDbModel)
}