package com.example.roomdatabaseapp.robot

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomdatabaseapp.data.RobotDatabase
import com.example.roomdatabaseapp.data.RobotRepository
import com.example.roomdatabaseapp.model.Robot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateRobotViewModel(context: Context): ViewModel() {
    private val repository: RobotRepository

    init {
        val robotDao = RobotDatabase.getDataBase(context).robotDao()
        repository = RobotRepository.getRobotRepository(robotDao)
    }

    fun updateRobot(robot: Robot) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRobot(robot)
        }
    }

    companion object {
        fun Factory(context: Context): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                UpdateRobotViewModel(context)
            }
        }
    }
}