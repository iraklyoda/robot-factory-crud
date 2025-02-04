package com.example.roomdatabaseapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdatabaseapp.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class RobotPurpose(val img: Int = 0) : Parcelable {
    KILL(img = R.drawable.robot_evil),
    DANCE(img = R.drawable.robot_dance),
    TEACH(img = R.drawable.robot_teach),
    CARE(img = R.drawable.robot_care)
}

@Parcelize
@Entity(tableName = "robot_table")
data class Robot(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val purpose: RobotPurpose,
    val iq: Int
) : Parcelable