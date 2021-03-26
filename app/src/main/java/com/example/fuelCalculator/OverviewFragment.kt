package com.example.fuelCalculator

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*


class OverviewFragment : Fragment() {
    private lateinit var vehicleQuantityInput: TextInputLayout
    private lateinit var peopleInput: TextInputLayout
    private lateinit var consumptionInput: TextInputLayout
    private lateinit var gasPriceInput: TextInputLayout
    private lateinit var distanceInput: TextInputLayout
    private lateinit var vehicleQuantityEditText: TextInputEditText
    private lateinit var toggle: ToggleButton
    private lateinit var mContext: Context

    private var gasPrice: Double = 0.toDouble()
    private var totalDistance: Double = 0.toDouble()
    private var totalPrice: Double = 0.toDouble()
    private var pricePerPerson: Double = 0.toDouble()
    private var gasConsumption: Double = 0.toDouble()
    private var averageConsumption: Double = 0.toDouble()
    private var eachVehicleConsumption: Double = 0.toDouble()
    private var vehicleQuantity: Int = 0
    private var people: Int = 0
    private var i: Int = 0
    private var sc = Scanner(System.`in`)
    private lateinit var vehicleConsumption: DoubleArray
    private val formatter = DecimalFormat("#,##0.##")
    private lateinit var vehicleDistance: DoubleArray

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
        vehicleQuantityEditText = view.findViewById(R.id.vehicle_quantity)

        val toggleMultipleVehicle: ToggleButton = view.findViewById(R.id.multiple_vehicle_title)
        val toggleDifferentConsumption: ToggleButton = view.findViewById(R.id.same_consumption_toggle)
        val toggleSplit: ToggleButton = view.findViewById(R.id.split_vehicle_toggle)
        val confirmButton: Button = view.findViewById(R.id.btn_confirm)




        toggleMultipleVehicle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                vehicleQuantityInput.isEnabled = false
                toggleSplit.isEnabled = false
                toggleDifferentConsumption.isEnabled = false
                vehicleQuantityEditText.setText("1")
            }
            else {
                vehicleQuantityInput.isEnabled = true
                toggleSplit.isEnabled = true
                if (toggleSplit.isChecked) {
                    toggleDifferentConsumption.isEnabled = false
                }
                if (!toggleSplit.isChecked) {
                    toggleDifferentConsumption.isEnabled = true
                }
            }
        }

        toggleSplit.setOnCheckedChangeListener { _, isChecked ->
            toggleDifferentConsumption.isEnabled = isChecked
        }

        toggleDifferentConsumption.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // The toggle is enabled
            } else {
                // The toggle is disabled
            }
        }

        confirmButton.setOnClickListener {
            Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
        }


    }

    private fun calAndPrintConsumption() {
        formatter.roundingMode = RoundingMode.HALF_UP
        var j = 1
        if (vehicleQuantityInput.editText!!.text.toString().trim().toInt() == 1) {
            println(
                "\nVehicle " + "consumes : " + formatter.format(vehicleDistance[i]) + "/"
                        + formatter.format(vehicleConsumption[i]) + " ~ " + formatter.format(vehicleDistance[i] / vehicleConsumption[i]) + " liter(s)"
            )
        } else {
            while (i < vehicleQuantityInput.editText!!.text.toString().trim().toInt()) {
                println(
                    "\nVehicle " + j + " consumes : " + formatter.format(vehicleDistance[i]) + "/"
                            + formatter.format(vehicleConsumption[i]) + " ~ " + formatter.format(vehicleDistance[i] / vehicleConsumption[i]) + " liter(s)"
                )
                j++
                i++
            }
        }
    }

    fun vehicleDistances(): DoubleArray {
        vehicleDistance = DoubleArray(vehicleQuantity)
        var j = 1

        if (vehicleQuantity == 1) {
            print("Enter vehicle's distances: ")
            vehicleDistance[i] = sc.nextDouble()
        } else {
            while (i < vehicleQuantity) {
                print("Enter vehicle $j's distances : ")
                vehicleDistance[i] = sc.nextDouble()
                j++
                i++
            }
        }
        return vehicleDistance
    }

    internal fun consumeVehicles(): DoubleArray {
        vehicleConsumption = DoubleArray(vehicleQuantity)
        var j = 1
        if (vehicleQuantity == 1) {
            print("Enter vehicle's consumption(km/liter) : ")
            vehicleConsumption[i] = sc.nextDouble()
        } else {
            while (i < vehicleQuantity) {
                print("Enter vehicle $j's consumption(km/liter) : ")
                vehicleConsumption[i] = sc.nextDouble()
                j++
                i++
            }
        }
        return vehicleConsumption
    }

    fun calDistance() {
        val j = 1
        while (i < vehicleQuantity) {
            println("\nVehicle " + j + "travels " + vehicleDistance[i] + "kilometers")
            i++
        }
    }




    internal fun printCostPerDistance() {
        formatter.roundingMode = RoundingMode.HALF_UP
        if (vehicleQuantity == 1) {
            val pricePerDistance = gasPrice() / calAvgConsumption()
            print("Cost per distance(baht/kilometers) : " + formatter.format(pricePerDistance) + " baht\n")
        }
    }

    private fun calEachVehicleConsumption(): Double {
        while (i < vehicleQuantity) {
//            eachVehicleConsumption = eachVehicleConsumption + vehicleDistance[i] / vehicleConsumption[i]
            eachVehicleConsumption += vehicleDistance[i] / vehicleConsumption[i]
            i++
        }
        return eachVehicleConsumption
    }

    private fun vehiclesAmount(): Int {
        this.vehicleQuantity = vehicleQuantity
        return vehicleQuantity
    }

    private fun gasPrice(): Double {
        this.gasPrice = gasPrice
        return gasPrice
    }

    private fun people(): Int {
        this.people = people
        return people
    }

    private fun totalDistanceTraveled(): Double {
        var sum = 0.0
        while (i < vehicleQuantity) {
            sum += vehicleDistance[i]
            i++
        }
        totalDistance = sum
        return totalDistance
    }

    private fun gasConsumed(): Double {
        gasConsumption += calEachVehicleConsumption()
        return gasConsumption
    }

    private fun totalPrice(): Double {
        totalPrice = if (vehicleQuantity == 1) {
            gasConsumed() * gasPrice
        } else {
            gasConsumption * gasPrice
        }
        return totalPrice
    }

    private fun pricePerPerson(): Double {
        pricePerPerson = totalPrice / people
        return pricePerPerson
    }

    private fun calAvgConsumption(): Double {
        var sum = 0.0
        while (i < vehicleQuantity) {
            sum += vehicleConsumption[i]
            i++
        }
        averageConsumption = sum / vehicleQuantity
        return averageConsumption
    }

    internal fun printEachVehicleDistance() {
        formatter.roundingMode = RoundingMode.HALF_UP
        print("\nTotal distance is ")

        if (vehicleQuantity == 1) {
            print(formatter.format(totalDistanceTraveled()) + " kilometers")
        } else {
            while (i < vehicleQuantity) {
                val s = "+" + formatter.format(vehicleDistance[i])
                if (i == 0) {
                    print(s.substring(1))
                } else {
                    print(s)
                }
                i++
            }
            print(" ~ " + formatter.format(totalDistanceTraveled()) + " kilometers")
        }
    }

    internal fun printAvgConsumption() {
        formatter.roundingMode = RoundingMode.HALF_UP
        if (vehicleQuantity == 1) {
            return
        } else {
            print("\nAverage consumption : (")

            while (i < vehicleQuantity) {
                val s = "+" + formatter.format(vehicleConsumption[i])
                if (i == 0) {
                    print(s.substring(1))
                } else {
                    print(s)
                }
                i++
            }
            print(")/" + vehicleQuantity + " ~ " + formatter.format(calAvgConsumption()) + " km/liter\n")
        }
    }

    internal fun printTotalGasConsume() {
        formatter.roundingMode = RoundingMode.HALF_UP
        //in case of 1 car just do not do this method
        if (vehicleQuantity == 1) {
            return
        } else {
            print("\nTotal gas consumed : ")
            while (i < vehicleQuantity) {
                val s = "+" + formatter.format(vehicleDistance[i] / vehicleConsumption[i])
                if (i == 0) {
                    print(s.substring(1))
                } else {
                    print(s)
                }
                i++
            }
            print(" ~ " + formatter.format(gasConsumed()) + " liter(s)\n")
        }
    }

    internal fun printEachVehicleConsume() {
        formatter.roundingMode = RoundingMode.HALF_UP
        var j = 1
        if (vehicleQuantity == 1) {
            println(
                "Gasoline price is " + formatter.format(vehicleDistance[i] / vehicleConsumption[i]) + "*"
                        + formatter.format(gasPrice) + " ~ " + formatter.format(vehicleDistance[i] / vehicleConsumption[i] * gasPrice) + " baht"
            )
        } else {
            while (i < vehicleQuantity) {
                println(
                    "\nVehicle " + j + "'s gasoline price is " + formatter.format(vehicleDistance[i] / vehicleConsumption[i]) + "*"
                            + formatter.format(gasPrice) + " ~ " + formatter.format(vehicleDistance[i] / vehicleConsumption[i] * gasPrice) + " baht"
                )
                j++
                i++
            }
        }
    }

    internal fun printTotalPrice() {
        formatter.roundingMode = RoundingMode.HALF_UP
        if (vehicleQuantity == 1) {
            return
        } else {
            print("\nTotal price : ")
            while (i < vehicleQuantity) {
                val s = "+" + formatter.format(vehicleDistance[i] / vehicleConsumption[i] * gasPrice)
                if (i == 0) {
                    print(s.substring(1))
                } else {
                    print(s)
                }
                i++
            }
            print(" ~ " + formatter.format(totalPrice()) + " Baht\n")
        }
    }

    internal fun printPricePerPerson() {
        formatter.roundingMode = RoundingMode.HALF_UP
        if (people() == 1) {
            return
        } else {
            print(
                "Price per person : " + formatter.format(totalPrice()) + "/" + people + " ~ "
                        + formatter.format(pricePerPerson()) + " Baht\n"
            )
        }
    }

}
