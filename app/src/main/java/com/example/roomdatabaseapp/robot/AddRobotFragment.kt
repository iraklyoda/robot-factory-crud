package com.example.roomdatabaseapp.robot

import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.roomdatabaseapp.BaseFragment
import com.example.roomdatabaseapp.data.Robot
import com.example.roomdatabaseapp.databinding.FragmentAddRobotBinding
import com.example.roomdatabaseapp.utils.checkEmpty
import com.example.roomdatabaseapp.utils.getString

class AddRobotFragment : BaseFragment<FragmentAddRobotBinding>(FragmentAddRobotBinding::inflate) {

    private val addRobotViewModel: AddRobotViewModel by viewModels() {
        AddRobotViewModel.Factory(context = requireContext())
    }

    override fun start() {
        handleInputs()
        addRobot()
        returnHome()
    }

    private fun handleInputs() {
        with(binding) {
            etName.doOnTextChanged { value, _, _, _ ->
                tvRobotName.text = value.toString()
            }
            etPurpose.doOnTextChanged { value, _, _, _ ->
                tvRobotPurpose.text = value.toString()
            }
            etIq.doOnTextChanged { value, _, _, _ ->
                tvRobotIq.text = value.toString()
            }
        }
    }

    private fun addRobot() {
        binding.btnBuild.setOnClickListener {
            val newRobot = validateForm()
            if (newRobot != null) {
                addRobotViewModel.addRobot(newRobot)
                findNavController().navigateUp()
            }
        }
    }

    private fun returnHome() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun validateForm(): Robot? {
        with(binding) {
            if (etName.checkEmpty()) {
                Toast.makeText(requireContext(), "Name is required", Toast.LENGTH_SHORT).show()
                return null
            }
            if (etPurpose.checkEmpty()) {
                Toast.makeText(requireContext(), "Purpose is required", Toast.LENGTH_SHORT).show()
                return null
            }
            if (etIq.checkEmpty()) {
                Toast.makeText(requireContext(), "IQ is required", Toast.LENGTH_SHORT).show()
                return null
            }
            return Robot(
                id = 0,
                name = etName.getString(),
                purpose = etPurpose.getString(),
                iq = etIq.getString().toInt()
            )
        }
    }
}