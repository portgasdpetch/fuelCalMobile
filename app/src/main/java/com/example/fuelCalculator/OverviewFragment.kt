package com.example.fuelCalculator

import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException


class OverviewFragment : Fragment() {
    private lateinit var vehicleQuantityInput: TextInputLayout
    private lateinit var peopleInput: TextInputLayout
    private lateinit var consumptionInput: TextInputLayout
    private lateinit var gasPriceInput: TextInputLayout
    private lateinit var distanceInput: TextInputLayout
    private lateinit var distanceInputEditText : TextInputEditText
    private lateinit var gasPriceEditText : TextInputEditText
    private lateinit var consumptionEditText: TextInputEditText
    private lateinit var peopleEditText: TextInputEditText
    private lateinit var output: TextView
    private lateinit var outputAns: TextView
    private lateinit var line2: View

//    var distanceInputEditTextString = distanceInputEditText.text.toString()
//    var distanceInputEditTextNumber = distanceInputEditTextString.toDouble()
//    var gasPriceEditTextString = gasPriceEditText.text.toString()
//    var gasPriceEditTextNumber = gasPriceEditTextString.toDouble()
//    var consumptionEditTextString = consumptionEditText.text.toString()
//    var consumptionEditTextNumber = consumptionEditTextString.toDouble()
//    var peopleEditTextString = peopleEditText.text.toString()
//    var peopleEditTextNumber = peopleEditTextString.toInt()


    private var gasConsumed:Double = 0.0
    private var usedGasPrice:Double=0.0
    private var gasPricePerPerson:Double=0.0
    private var pricePerDistance:Double=0.0

    private var gasPrice: Double = 0.toDouble()
    private var totalDistance: Double = 0.toDouble()
    private var totalPrice: Double = 0.toDouble()
    private var pricePerPerson: Double = 0.toDouble()
    private var gasConsumption: Double = 0.toDouble()
    private var averageConsumption: Double = 0.toDouble()
    private var eachVehicleConsumption: Double = 0.toDouble()
    private var vehicleQuantity: Int = 1
    private var people: Int = 0
    private var i: Int = 0
    private var sc = Scanner(System.`in`)
    private lateinit var vehicleConsumption: DoubleArray
    private val formatter = DecimalFormat("#,##0.##")
    private val nf = NumberFormat.getInstance(Locale.getDefault())
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
//        vehicleQuantityInput = view.findViewById(R.id.vehicle_quantity_text_input)
        consumptionInput = view.findViewById(R.id.vehicle_consumption_text_input)
        distanceInput = view.findViewById(R.id.vehicle_distance_text_input)
        peopleInput = view.findViewById(R.id.people_text_input)
        gasPriceInput = view.findViewById(R.id.gas_price_text_input)

        distanceInputEditText = view.findViewById(R.id.vehicle_distance)
        gasPriceEditText = view.findViewById(R.id.gas_price)
        consumptionEditText = view.findViewById(R.id.vehicle_consumption)
        peopleEditText = view.findViewById(R.id.people)

        line2 = view.findViewById(R.id.view2)
        output = view.findViewById(R.id.output)
        outputAns = view.findViewById(R.id.outputAns)



        //limiting input decimal
        gasPriceEditText.inputFilterDecimal(
            // this values must be positive (0+) unless it throw exception
            maxDigitsIncludingPoint = 5,
            maxDecimalPlaces = 2
        )
        consumptionEditText.inputFilterDecimal(
            maxDigitsIncludingPoint = 5,maxDecimalPlaces = 2
        )
        distanceInputEditText.inputFilterDecimal(
            maxDigitsIncludingPoint = 6,
            maxDecimalPlaces = 3
        )
        peopleEditText.inputFilterDecimal(
            maxDigitsIncludingPoint = 2,
            maxDecimalPlaces = 1
        )

//        vehicleQuantityEditText = view.findViewById(R.id.vehicle_quantity)
//        picker1 = view.findViewById(R.id.vehicle_amount)
//        picker1.maxValue = 4
//        picker1.minValue = 1
//        picker1.wrapSelectorWheel = true


//        val toggleMultipleVehicle: ToggleButton = view.findViewById(R.id.multiple_vehicle_title)
//        val toggleDifferentConsumption: ToggleButton = view.findViewById(R.id.same_consumption_toggle)
//        val toggleSplit: ToggleButton = view.findViewById(R.id.split_vehicle_toggle)
        val confirmButton: Button = view.findViewById(R.id.btn_confirm)
//        val button1 = view.findViewById<Button>(R.id.outlinedButton1)
//        val button2 = view.findViewById<Button>(R.id.outlinedButton2)
//        val button3 = view.findViewById<Button>(R.id.outlinedButton3)
//        val button4 = view.findViewById<Button>(R.id.outlinedButton4)

//        button1.setOnClickListener {
//            button1.setBackgroundColor(Color.parseColor("#FFBB33"))
//            button2.setBackgroundColor(Color.TRANSPARENT)
//            button3.setBackgroundColor(Color.TRANSPARENT)
//            button4.setBackgroundColor(Color.TRANSPARENT)
//            vehicleQuantityInput.visibility = View.GONE
//        }

//        button2.setOnClickListener{
//            button2.setBackgroundColor(Color.parseColor("#FFBB33"))
//            button1.setBackgroundColor(Color.TRANSPARENT)
//            button3.setBackgroundColor(Color.TRANSPARENT)
//            button4.setBackgroundColor(Color.TRANSPARENT)
//            vehicleQuantityInput.visibility = View.GONE
//        }

//        button3.setOnClickListener{
//            button3.setBackgroundColor(Color.parseColor("#FFBB33"))
//            button2.setBackgroundColor(Color.TRANSPARENT)
//            button1.setBackgroundColor(Color.TRANSPARENT)
//            button4.setBackgroundColor(Color.TRANSPARENT)
//            vehicleQuantityInput.visibility = View.GONE
//        }

//        button4.setOnClickListener{
//            button4.setBackgroundColor(Color.parseColor("#FFBB33"))
//            button2.setBackgroundColor(Color.TRANSPARENT)
//            button3.setBackgroundColor(Color.TRANSPARENT)
//            button1.setBackgroundColor(Color.TRANSPARENT)
//            vehicleQuantityInput.visibility = View.VISIBLE
//        }




//
//        toggleMultipleVehicle.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                vehicleQuantityInput.isEnabled = false
//                toggleSplit.isEnabled = false
//                toggleDifferentConsumption.isEnabled = false
//                vehicleQuantityEditText.setText("1")
//            }
//            else {
//                vehicleQuantityInput.isEnabled = true
//                toggleSplit.isEnabled = true
//                if (toggleSplit.isChecked) {
//                    toggleDifferentConsumption.isEnabled = false
//                }
//                if (!toggleSplit.isChecked) {
//                    toggleDifferentConsumption.isEnabled = true
//                }
//            }
//        }
//
//        toggleSplit.setOnCheckedChangeListener { _, isChecked ->
//            toggleDifferentConsumption.isEnabled = isChecked
//        }
//
//        toggleDifferentConsumption.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                // The toggle is enabled
//            } else {
//                // The toggle is disabled
//            }
//        }

        confirmButton.setOnClickListener {
            calAndPrintAll()
//            Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
        }

    }


    private fun EditText.inputFilterDecimal(
        // maximum digits including point and without decimal places
        maxDigitsIncludingPoint: Int,
        maxDecimalPlaces: Int // maximum decimal places
    ){
        try {
            filters = arrayOf<InputFilter>(
                DecimalDigitsInputFilter(maxDigitsIncludingPoint, maxDecimalPlaces)
            )
        }catch (e: PatternSyntaxException){
            isEnabled = false
            hint = e.message
        }
    }

    class DecimalDigitsInputFilter(
        maxDigitsIncludingPoint: Int, maxDecimalPlaces: Int
    ) : InputFilter {
        private val pattern: Pattern = Pattern.compile(
            ("[0-9]{0," + (maxDigitsIncludingPoint-1) + "}+((\\.[0-9]{0," + (maxDecimalPlaces-1) + "})?)||(\\.)?")
        )

        override fun filter(
            p0: CharSequence?, p1: Int, p2: Int, p3: Spanned?, p4: Int, p5: Int
        ): CharSequence? {
            p3?.apply {
                val matcher: Matcher = pattern.matcher(p3)
                return if (!matcher.matches()) "" else null
            }
            return null
        }
    }


    private fun calAndPrintAll() {
        vehicleQuantity = 1
        formatter.roundingMode = RoundingMode.HALF_UP
        val distanceInputEditTextString = distanceInputEditText.text.toString()
        val distanceInputEditTextNumber = distanceInputEditTextString.toDouble()
        val gasPriceEditTextString = gasPriceEditText.text.toString()
        val gasPriceEditTextNumber = gasPriceEditTextString.toDouble()
        val consumptionEditTextString = consumptionEditText.text.toString()
        val consumptionEditTextNumber = consumptionEditTextString.toDouble()
        val peopleEditTextString = peopleEditText.text.toString()
        val peopleEditTextNumber = peopleEditTextString.toInt()

        if (distanceInputEditTextString.isBlank() || gasPriceEditTextString.isBlank() || consumptionEditTextString.isBlank() || peopleEditTextString.isBlank()) {
            Toast.makeText(context, "Please fill the empty field(s)", Toast.LENGTH_SHORT).show()
        } else {
            line2.visibility = View.VISIBLE
            output.visibility = View.VISIBLE
            outputAns.visibility = View.VISIBLE
//            output.text = " Total distances travelled : " + formatter.format(distanceInputEditTextNumber) +
//                    "\n Total gas consumed : " + calGasConsumed() +
//                    "\n Total people : " + formatter.format(peopleEditTextNumber) +
//                    "\n Total gas price : " + formatter.format(distanceInputEditTextNumber/consumptionEditTextNumber*gasPriceEditTextNumber) +
//                    "\n Gas price per person : " + formatter.format(distanceInputEditTextNumber/consumptionEditTextNumber*gasPriceEditTextNumber/peopleEditTextNumber)

            //java style
            // output.text = " Total distances travelled : " + formatter.format(distanceInputEditTextNumber) +
                    // "\n Total gas consumed : " + formatter.format(distanceInputEditTextNumber) + " / " + formatter.format(consumptionEditTextNumber) +
                    // " = " + formatter.format(distanceInputEditTextNumber/consumptionEditTextNumber) +
                    // "\n Total people : " + formatter.format(peopleEditTextNumber) +
                    // "\n Total gas price : " + formatter.format(distanceInputEditTextNumber/consumptionEditTextNumber) + " * " +
                    // formatter.format(gasPriceEditTextNumber)+ " = " + formatter.format(distanceInputEditTextNumber/consumptionEditTextNumber*gasPriceEditTextNumber) +
                    // "\n Gas price per person : " + formatter.format(distanceInputEditTextNumber/consumptionEditTextNumber*gasPriceEditTextNumber) + " / " +
                    // formatter.format(peopleEditTextNumber) + " = " + formatter.format(distanceInputEditTextNumber/consumptionEditTextNumber*gasPriceEditTextNumber/peopleEditTextNumber) +
                    // "\n Price per distance(baht/kilometer) : " + formatter.format(gasPriceEditTextNumber) + " / " + formatter.format(consumptionEditTextNumber) + " = " +
                    // formatter.format(gasPriceEditTextNumber/consumptionEditTextNumber);

            //kotlin style
            calGasConsumed()
            calUsedGasPrice()
            calGasPricePerPerson()
            calPricePerDistance()
            output.text = " Distances travelled : ${formatter.format(distanceInputEditTextNumber)}" +
                    " \n Consumption : ${formatter.format(consumptionEditTextNumber)}" +
                    " \n Gas Price : ${formatter.format(gasPriceEditTextNumber)}" +
                    " \n Population : ${(peopleEditTextNumber)}" +
                    " ${System.lineSeparator()} " +
                    " \n Gas consumed : ${formatter.format(distanceInputEditTextNumber)} / ${formatter.format(consumptionEditTextNumber)} ~ ${formatter.format(gasConsumed)} liters" +
                    " \n Used gas's price ${formatter.format(gasConsumed)} * ${formatter.format(gasPriceEditTextNumber)} ~ ${formatter.format(usedGasPrice)} baht" +
                    " \n Gas price per person : ${formatter.format(distanceInputEditTextNumber/consumptionEditTextNumber*gasPriceEditTextNumber)} / ${formatter.format(peopleEditTextNumber)} ~ ${formatter.format(gasPricePerPerson)} baht" +
                    " \n Price per distance : ${formatter.format(gasPriceEditTextNumber)} / ${formatter.format(consumptionEditTextNumber)} ~ ${formatter.format(pricePerDistance)} baht/kilometer"
        }
    }

    private fun calGasConsumed(): Double {
        val distanceInputEditTextString = distanceInputEditText.text.toString()
        val distanceInputEditTextNumber = distanceInputEditTextString.toDouble()
        val gasPriceEditTextString = gasPriceEditText.text.toString()
        val gasPriceEditTextNumber = gasPriceEditTextString.toDouble()
        val consumptionEditTextString = consumptionEditText.text.toString()
        val consumptionEditTextNumber = consumptionEditTextString.toDouble()
        val peopleEditTextString = peopleEditText.text.toString()
        val peopleEditTextNumber = peopleEditTextString.toInt()
        formatter.roundingMode = RoundingMode.HALF_UP
        val calculatedGasConsumed:String = formatter.format(distanceInputEditTextNumber/consumptionEditTextNumber)
        gasConsumed = nf.parse(calculatedGasConsumed).toDouble()
        formatter.format(gasConsumed)
        return gasConsumed
    }

    private fun calUsedGasPrice():Double{
        val distanceInputEditTextString = distanceInputEditText.text.toString()
        val distanceInputEditTextNumber = distanceInputEditTextString.toDouble()
        val gasPriceEditTextString = gasPriceEditText.text.toString()
        val gasPriceEditTextNumber = gasPriceEditTextString.toDouble()
        val consumptionEditTextString = consumptionEditText.text.toString()
        val consumptionEditTextNumber = consumptionEditTextString.toDouble()
        val peopleEditTextString = peopleEditText.text.toString()
        val peopleEditTextNumber = peopleEditTextString.toInt()
        formatter.roundingMode = RoundingMode.HALF_UP
        val calculatedUsedGasPrice = formatter.format(distanceInputEditTextNumber/consumptionEditTextNumber*gasPriceEditTextNumber)
        usedGasPrice = nf.parse(calculatedUsedGasPrice).toDouble()
        formatter.format(usedGasPrice)
        return usedGasPrice
    }

    private fun calGasPricePerPerson():Double{
        val distanceInputEditTextString = distanceInputEditText.text.toString()
        val distanceInputEditTextNumber = distanceInputEditTextString.toDouble()
        val gasPriceEditTextString = gasPriceEditText.text.toString()
        val gasPriceEditTextNumber = gasPriceEditTextString.toDouble()
        val consumptionEditTextString = consumptionEditText.text.toString()
        val consumptionEditTextNumber = consumptionEditTextString.toDouble()
        val peopleEditTextString = peopleEditText.text.toString()
        val peopleEditTextNumber = peopleEditTextString.toInt()
        formatter.roundingMode = RoundingMode.HALF_UP
        val calculatedGasPricePerPerson = formatter.format(distanceInputEditTextNumber/consumptionEditTextNumber*gasPriceEditTextNumber/peopleEditTextNumber)
        gasPricePerPerson = nf.parse(calculatedGasPricePerPerson).toDouble()
        formatter.format(gasPricePerPerson)
        return gasPricePerPerson
    }

    private fun calPricePerDistance(): Double {
        formatter.roundingMode = RoundingMode.HALF_UP
        val distanceInputEditTextString = distanceInputEditText.text.toString()
        val distanceInputEditTextNumber = distanceInputEditTextString.toDouble()
        val gasPriceEditTextString = gasPriceEditText.text.toString()
        val gasPriceEditTextNumber = gasPriceEditTextString.toDouble()
        val consumptionEditTextString = consumptionEditText.text.toString()
        val consumptionEditTextNumber = consumptionEditTextString.toDouble()
        val peopleEditTextString = peopleEditText.text.toString()
        val peopleEditTextNumber = peopleEditTextString.toInt()
        val calculatedPricePerDistance = formatter.format(gasPriceEditTextNumber/consumptionEditTextNumber)
        pricePerDistance = nf.parse(calculatedPricePerDistance).toDouble()
        formatter.format(pricePerDistance)
        return pricePerDistance
    }


    private fun calAndPrintConsumption() {
        var j = 1
//            println(
//                "\nVehicle " + "consumes : " + distanceInputEditTextNumber + "/"
//                        + formatter.format(vehicleConsumption[i]) + " ~ " + formatter.format(vehicleDistance[i] / vehicleConsumption[i]) + " liter(s)"
//            )

        //for long version
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
