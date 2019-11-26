package com.crystalpigeon.busnovisad.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Lane::class, Schedule::class, FavouriteLane::class], version = 2, exportSchema = false)
abstract class BusDatabase : RoomDatabase() {

    abstract fun lanesDao(): LanesDao
    abstract fun favLanesDao(): FavouriteLanesDao
    abstract fun schedulesDao(): SchedulesDao

    companion object {
        private var instance: BusDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): BusDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context,
                    BusDatabase::class.java,
                    "bus_database")
                    .fallbackToDestructiveMigration()//TODO remove for production
                    .build()
            }

            return instance as BusDatabase
        }
    }
}