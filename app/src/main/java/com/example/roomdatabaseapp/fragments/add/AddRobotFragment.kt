package com.example.roomdatabaseapp.fragments.add

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.roomdatabaseapp.BaseFragment
import com.example.roomdatabaseapp.R
import com.example.roomdatabaseapp.model.Robot
import com.example.roomdatabaseapp.model.RobotPurpose
import com.example.roomdatabaseapp.databinding.FragmentAddRobotBinding
import com.example.roomdatabaseapp.robot.AddRobotViewModel
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
        setUpSpinner()
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

    private fun setUpSpinner() {
        val adapter = ArrayAdapter(
            requireContext(), R.layout.spinner_header_item,
            RobotPurpose.entries.toTypedArray()
        )
        adapter.setDropDownViewResource(R.layout.spinner_item)
        binding.spinnerPurpose.adapter = adapter
    }

    private fun validateForm(): Robot? {
        with(binding) {
            if (etName.checkEmpty()) {
                Toast.makeText(requireContext(),
                    getString(R.string.name_is_required), Toast.LENGTH_SHORT).show()
                return null
            }
            if (etIq.checkEmpty()) {
                Toast.makeText(requireContext(),
                    getString(R.string.iq_is_required), Toast.LENGTH_SHORT).show()
                return null
            }
            return Robot(
                id = 0,
                name = etName.getString().uppercase(),
                purpose = RobotPurpose.valueOf(spinnerPurpose.selectedItem.toString()),
                iq = etIq.getString().toInt()
            )
        }
    }
}