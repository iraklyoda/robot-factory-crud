package com.example.roomdatabaseapp.data

import androidx.room.TypeConverter
import com.example.roomdatabaseapp.model.RobotPurpose

class Converters {
    @TypeConverter
    fun fromRobotPurpose(purpose: RobotPurpose): String {
        return purpose.name
    }

    @TypeConverter
    fun toRobotPurpose(purpose: String): RobotPurpose {
        return RobotPurpose.valueOf(purpose)
    }
}