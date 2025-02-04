package com.example.roomdatabaseapp.fragments.update

import android.util.Log.d
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabaseapp.BaseFragment
import com.example.roomdatabaseapp.R
import com.example.roomdatabaseapp.databinding.FragmentUpdateRobotBinding
import com.example.roomdatabaseapp.model.Robot
import com.example.roomdatabaseapp.model.RobotPurpose
import com.example.roomdatabaseapp.robot.UpdateRobotViewModel
import com.example.roomdatabaseapp.utils.checkEmpty
import com.example.roomdatabaseapp.utils.getString

class UpdateRobotFragment :
    BaseFragment<FragmentUpdateRobotBinding>(FragmentUpdateRobotBinding::inflate) {

    private val args by navArgs<UpdateRobotFragmentArgs>()

    private val updateRobotViewModel: UpdateRobotViewModel by viewModels() {
        UpdateRobotViewModel.Factory(context = requireContext())
    }

    override fun start() {
        returnHome()
        setUpSpinner()
        setInputs()
        handleInputs()
        rebuildRobot()
    }

    private fun returnHome() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setUpSpinner() {
        val robotPurposes = RobotPurpose.entries.toTypedArray()

        val adapter = ArrayAdapter(
            requireContext(), R.layout.spinner_header_item,
            robotPurposes
        )
        adapter.setDropDownViewResource(R.layout.spinner_item)
        binding.spinnerPurpose.adapter = adapter

        val currentPurpose = args.currentRobot.purpose
        val position = robotPurposes.indexOf(currentPurpose)

        if (position != -1) {
            binding.spinnerPurpose.setSelection(position)
        }
    }

    private fun setInputs() {
        binding.apply {
            etName.setText(args.currentRobot.name)
            tvRobotName.text = args.currentRobot.name

            etIq.setText(args.currentRobot.iq.toString())
            tvRobotIq.text = getString(R.string.iq_update, args.currentRobot.iq.toString())
        }
    }

    private fun handleInputs() {
        with(binding) {
            etName.doOnTextChanged { value, _, _, _ ->
                tvRobotName.text = value.toString()
            }
            etIq.doOnTextChanged { value, _, _, _ ->
                tvRobotIq.text = getString(R.string.iq, value.toString())
            }

            spinnerPurpose.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = parent?.getItemAtPosition(position).toString()
                    tvRobotPurpose.text = selectedItem
                    ivRobot.setImageResource(RobotPurpose.valueOf(selectedItem).img)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    private fun validateForm(): Robot? {
        with(binding) {
            if (etName.checkEmpty()) {
                Toast.makeText(requireContext(), "Name is required", Toast.LENGTH_SHORT).show()
                return null
            }
            if (etIq.checkEmpty()) {
                Toast.makeText(requireContext(), "IQ is required", Toast.LENGTH_SHORT).show()
                return null
            }
            return Robot(
                id = args.currentRobot.id,
                name = etName.getString().uppercase(),
                purpose = RobotPurpose.valueOf(spinnerPurpose.selectedItem.toString()),
                iq = etIq.getString().toInt()
            )
        }
    }

    private fun rebuildRobot() {
        binding.btnBuild.setOnClickListener {
            val robot = validateForm()
            if (robot != null) {
                updateRobotViewModel.updateRobot(robot)
                Toast.makeText(
                    requireContext(),
                    "Robot ${robot.name} has been rebuilt",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigateUp()
            }
        }
    }
}