package com.example.fuelCalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ToggleButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var drawer: DrawerLayout
    private var backPressedTime: Long = 0.toLong()

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
    private lateinit var vehicleDistance: DoubleArray
    private lateinit var vehicleConsumption: DoubleArray
    private val formatter = DecimalFormat("#,##0.##")

    private lateinit var context: Context

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_home) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, OverviewFragment()).commit()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        drawer = findViewById(R.id.drawer_layout)
        var navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        this@MainActivity.title = ("Fuel Calculator") // default toolbar title

        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                OverviewFragment()
            ).commit()
            navigationView.setCheckedItem(R.id.nav_home)
            navigationView.menu.getItem(1).setActionView(R.layout.menu_image)
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


    internal fun calAndPrintConsumption() {
        formatter.roundingMode = RoundingMode.HALF_UP
        var j = 1
        if (vehicleQuantity == 1) {
            println(
                "\nVehicle " + "consumes : " + formatter.format(vehicleDistance[i]) + "/"
                        + formatter.format(vehicleConsumption[i]) + " ~ " + formatter.format(vehicleDistance[i] / vehicleConsumption[i]) + " liter(s)"
            )
        } else {
            while (i < vehicleQuantity) {
                println(
                    "\nVehicle " + j + " consumes : " + formatter.format(vehicleDistance[i]) + "/"
                            + formatter.format(vehicleConsumption[i]) + " ~ " + formatter.format(vehicleDistance[i] / vehicleConsumption[i]) + " liter(s)"
                )
                j++
                i++
            }
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
