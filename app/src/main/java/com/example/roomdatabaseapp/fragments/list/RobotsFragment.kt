package com.example.roomdatabaseapp.fragments.list

import android.app.AlertDialog
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaseapp.BaseFragment
import com.example.roomdatabaseapp.R
import com.example.roomdatabaseapp.databinding.FragmentRobotsBinding
import com.example.roomdatabaseapp.model.Robot
import com.example.roomdatabaseapp.robot.RobotsAdapter
import com.example.roomdatabaseapp.robot.RobotsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RobotsFragment : BaseFragment<FragmentRobotsBinding>(FragmentRobotsBinding::inflate) {

    private lateinit var robotsAdapter: RobotsAdapter

    private val robotsViewModel: RobotsViewModel by viewModels() {
        RobotsViewModel.Factory(context = requireContext())
    }

    override fun start() {
        navigateToCreation()
        setRobotAdapter()
        setRobots()
    }

    private fun navigateToCreation() {
        binding.btnAdd.setOnClickListener {
            val action = RobotsFragmentDirections.actionRobotsFragmentToAddRobotFragment()
            findNavController().navigate(action)
        }
    }

    private fun setRobotAdapter() {
        robotsAdapter = RobotsAdapter { robot ->
            deleteRobot(robot)
        }

        binding.rvRobots.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRobots.adapter = robotsAdapter
    }

    private fun deleteRobot(robot: Robot) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.delete_item))
            .setMessage(getString(R.string.are_you_sure_you_want_to_deactivate_this_robot))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                robotsViewModel.deleteRobot(robot)
                Toast.makeText(requireContext(),
                    getString(R.string.robot_terminated, robot.name), Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(getString(R.string.no), null)
            .show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }

    private fun setRobots() {
        viewLifecycleOwner.lifecycleScope.launch {
            robotsViewModel.getAllData.collectLatest { robots ->
                robotsAdapter.submitList(robots)
            }
        }
    }
}