package com.example.roomdatabaseapp.robot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseapp.data.Robot
import com.example.roomdatabaseapp.databinding.RobotItemBinding

class RobotsDiffUtil : DiffUtil.ItemCallback<Robot>() {
    override fun areItemsTheSame(oldItem: Robot, newItem: Robot): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Robot, newItem: Robot): Boolean {
        return oldItem == newItem
    }

}

class RobotsAdapter : ListAdapter<Robot, RobotsAdapter.RobotsViewHolder>(RobotsDiffUtil()) {
    inner class RobotsViewHolder(private val binding: RobotItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(robot: Robot) {
            binding.apply {
                tvRobotName.text = robot.name
                tvRobotPurpose.text = robot.purpose
                tvRobotIq.text = robot.iq.toString()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RobotsViewHolder {
        val binding: RobotItemBinding = RobotItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RobotsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RobotsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}