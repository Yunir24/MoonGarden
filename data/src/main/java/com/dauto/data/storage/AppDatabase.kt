package com.dauto.data.storage

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dauto.data.storage.model.moonCalendar.MoonDayDbModel
import com.dauto.data.storage.model.moonCalendar.MoonMonthDbModel
import com.dauto.data.storage.model.weatherDay.CurrentWeatherDbModel
import com.dauto.data.storage.model.weatherDay.HoursDbModel
import com.dauto.data.storage.model.weatherDay.WeatherDayDbModel

@Database(
    entities = [
        MoonDayDbModel::class,
        MoonMonthDbModel::class,
        CurrentWeatherDbModel::class,
        HoursDbModel::class,
        WeatherDayDbModel::class
    ], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): MoonСalendarDao
    abstract fun weatherDao(): WeatherDao

    companion object {

        private const val DB_NAME = "moon.db"
        private var INSTANCE: AppDatabase? = null
        private val lock = Any()


        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(lock) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
                    .createFromAsset("database/moon_calendar_1_0.db")
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}


//.fallbackToDestructiveMigration()
//.createFromAsset("database/databaseroom.db")