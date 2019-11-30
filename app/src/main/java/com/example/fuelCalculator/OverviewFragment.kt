package com.example.fuelCalculator

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*


class OverviewFragment : Fragment() {
    private lateinit var vehicleQuantityInput: TextInputLayout
    private lateinit var peopleInput: TextInputLayout
    private lateinit var consumptionInput: TextInputLayout
    private lateinit var gasPriceInput: TextInputLayout
    private lateinit var distanceInput: TextInputLayout
    private lateinit var toggle: ToggleButton
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vehicleQuantityInput = view.findViewById(R.id.vehicle_quantity_text_input)
        consumptionInput = view.findViewById(R.id.vehicle_consumption_text_input)
        distanceInput = view.findViewById(R.id.vehicle_distance_text_input)
        peopleInput = view.findViewById(R.id.people_text_input)
        gasPriceInput = view.findViewById(R.id.gas_price_text_input)
        toggle = view.findViewById(R.id.multiple_vehicle_toggle)

        val confirmButton: Button = view.findViewById(R.id.btn_confirm)
        confirmButton.setOnClickListener {
            Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
        }

        val toggleMultipleVehicle: ToggleButton = view.findViewById(R.id.multiple_vehicle_toggle)
        toggleMultipleVehicle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                vehicleQuantityInput.visibility = View.VISIBLE
            } else {
                vehicleQuantityInput.visibility = View.INVISIBLE
            }
        }

        val toggleSameConsumption: ToggleButton = view.findViewById(R.id.same_consumption_toggle)
        toggleMultipleVehicle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // The toggle is enabled
            } else {
                // The toggle is disabled
            }
        }
    }




}
