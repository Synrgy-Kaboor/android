package com.synrgy.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.synrgy.data.flight.local.AirportDao
import com.synrgy.data.flight.local.AirportEntity


/**
 * Created by wahid on 2/3/2024.
 * Github github.com/wahidabd.
 */


@Database(entities = [AirportEntity::class], version = 1, exportSchema = false)
abstract class KaboorDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: KaboorDatabase? = null

        fun getDatabase(context: Context): KaboorDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    KaboorDatabase::class.java,
                    "kaboor.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun flightDao(): AirportDao

}