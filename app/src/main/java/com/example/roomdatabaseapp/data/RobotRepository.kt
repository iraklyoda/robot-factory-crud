package com.example.roomdatabaseapp.data

import com.example.roomdatabaseapp.model.Robot
import kotlinx.coroutines.flow.Flow

class RobotRepository(private val robotDao: RobotDao) {

    val readAllData: Flow<List<Robot>> = robotDao.getAll()

    suspend fun addRobot(robot: Robot) {
        robotDao.addRobot(robot)
    }

    suspend fun updateUser(robot: Robot) {
        robotDao.updateRobot(robot)
    }

    companion object {
        @Volatile
        private var INSTANCE: RobotRepository? = null

        fun getRobotRepository(robotDao: RobotDao): RobotRepository {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = RobotRepository(robotDao)
                INSTANCE = instance
                return instance
            }
        }
    }
}