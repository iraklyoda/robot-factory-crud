package com.example.roomdatabaseapp.robot

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomdatabaseapp.model.Robot
import com.example.roomdatabaseapp.data.RobotDatabase
import com.example.roomdatabaseapp.data.RobotRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddRobotViewModel(context: Context): ViewModel() {
    private val repository: RobotRepository

    init {
        val robotDao = RobotDatabase.getDataBase(context).robotDao()
        repository = RobotRepository.getRobotRepository(robotDao)
    }

    fun addRobot(robot: Robot) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRobot(robot)
        }
    }

    companion object {
        fun Factory(context: Context): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AddRobotViewModel(context)
            }
        }
    }
}