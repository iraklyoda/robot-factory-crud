package com.example.roomdatabaseapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RobotDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addRobot(robot: Robot)

    @Query("SELECT * FROM robot_table ORDER BY id DESC")
    fun getAll(): Flow<List<Robot>>
}