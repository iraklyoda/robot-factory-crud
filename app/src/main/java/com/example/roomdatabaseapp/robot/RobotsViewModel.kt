package com.example.roomdatabaseapp.robot

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomdatabaseapp.data.Robot
import com.example.roomdatabaseapp.data.RobotDatabase
import com.example.roomdatabaseapp.data.RobotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RobotsViewModel(context: Context): ViewModel() {

    var getAllData: Flow<List<Robot>> = flowOf(listOf<Robot>())
    private val repository: RobotRepository

    init {
        val robotDao = RobotDatabase.getDataBase(context).robotDao()
        repository = RobotRepository.getRobotRepository(robotDao)
        getAllData = repository.readAllData
    }

    companion object {
        fun Factory(context: Context): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RobotsViewModel(context)
            }
        }
    }
}