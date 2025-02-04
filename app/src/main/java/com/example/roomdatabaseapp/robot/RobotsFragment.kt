package com.example.roomdatabaseapp.robot

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaseapp.BaseFragment
import com.example.roomdatabaseapp.databinding.FragmentRobotsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RobotsFragment : BaseFragment<FragmentRobotsBinding>(FragmentRobotsBinding::inflate) {

    private val robotsAdapter: RobotsAdapter by lazy {
        RobotsAdapter()
    }


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
        binding.rvRobots.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRobots.adapter = robotsAdapter
    }

    private fun setRobots() {
        viewLifecycleOwner.lifecycleScope.launch {
            robotsViewModel.getAllData.collectLatest { robots ->
                robotsAdapter.submitList(robots)
            }
        }
    }
}