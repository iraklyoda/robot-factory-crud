package com.example.roomdatabaseapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "robot_table")
data class Robot(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val purpose: String,
    val iq: Int
)