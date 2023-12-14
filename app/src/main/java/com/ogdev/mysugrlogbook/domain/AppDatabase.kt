package com.ogdev.mysugrlogbook.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ogdev.mysugrlogbook.logbook.data.dao.BloodGlucoseDao
import com.ogdev.mysugrlogbook.logbook.data.entity.BloodGlucoseEntity

private const val APP_DATABASE = "logbook_db"
private const val VERSION_DATABASE = 1

@Database(
    entities = [BloodGlucoseEntity::class],
    version = VERSION_DATABASE,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bloodGlucoseDao(): BloodGlucoseDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    APP_DATABASE
                ).build()
                INSTANCE = instance
                instance
            }
    }
}