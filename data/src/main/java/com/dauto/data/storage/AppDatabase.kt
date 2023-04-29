package com.dauto.data.storage

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dauto.data.storage.model.MoonDayDbModel
import com.dauto.data.storage.model.MoonMonthDbModel
import com.dauto.data.storage.model.WeatherDao
import com.dauto.data.storage.model.WeatherDay

@Database(
    entities = [MoonDayDbModel::class, MoonMonthDbModel::class, WeatherDay::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): MoonСalendarDao
    abstract fun weatherDao(): WeatherDao

    companion object {

        private const val DB_NAME = "moon_calendar2.db"
        private var INSTANCE: AppDatabase? = null
        private val lock = Any()



        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(lock){
                INSTANCE?.let{
                    return it
                }
                val db = Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}


//.fallbackToDestructiveMigration()
//.createFromAsset("database/databaseroom.db")