package com.example.roomdatabaseapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Robot::class], version = 1, exportSchema = false)
abstract class RobotDatabase : RoomDatabase() {

    abstract fun robotDao(): RobotDao

    companion object {
        @Volatile
        private var INSTANCE: RobotDatabase? = null

        fun getDataBase(context: Context): RobotDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RobotDatabase::class.java,
                    "robot_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}