package com.example.roomdatabaseapp.robot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseapp.R
import com.example.roomdatabaseapp.model.Robot
import com.example.roomdatabaseapp.databinding.RobotItemBinding
import com.example.roomdatabaseapp.fragments.list.RobotsFragmentDirections

class RobotsDiffUtil : DiffUtil.ItemCallback<Robot>() {
    override fun areItemsTheSame(oldItem: Robot, newItem: Robot): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Robot, newItem: Robot): Boolean {
        return oldItem == newItem
    }

}

class RobotsAdapter(
    private val onItemClick: (Robot) -> Unit
) : ListAdapter<Robot, RobotsAdapter.RobotsViewHolder>(RobotsDiffUtil()) {
    inner class RobotsViewHolder(private val binding: RobotItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(robot: Robot) {
            binding.apply {
                tvId.text = robot.id.toString()
                tvRobotName.text = robot.name
                tvRobotPurpose.text = robot.purpose.name
                tvRobotIq.text = itemView.context.getString(R.string.iq_adapter, robot.iq.toString())
                ivProfile.setImageResource(robot.purpose.img)
            }

            binding.root.setOnLongClickListener {
                val action =
                    RobotsFragmentDirections.actionRobotsFragmentToUpdateRobotFragment(robot)
                itemView.findNavController().navigate(action)
                true
            }

            binding.btnDelete.setOnClickListener {
                onItemClick(robot)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RobotsViewHolder {
        val binding: RobotItemBinding =
            RobotItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RobotsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RobotsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}