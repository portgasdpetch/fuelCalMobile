package com.example.fuelCalculator

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_shuffle.*
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private var backPressedTime: Long = 0.toLong()

    private var recyclerView: RecyclerView? = null
    private var movies = listOf("Ae1",
        "Ae2",
        "Ae3",
        "Ae4")

    private lateinit var context: Context

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_calculator) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                OverviewFragment()
            ).commit()
            this@MainActivity.title = ("Fuel Calculator")
            toolbar.setBackgroundColor(getColor(R.color.colorPrimary))
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }
        if (item.itemId == R.id.nav_shuffle) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                ShuffleFragment()
            ).commit()
            this@MainActivity.title = ("Balance Shuffle")
            toolbar.setBackgroundColor(getColor(R.color.colorSecondary))
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorSecondaryDark)
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        val itemAdapter = MyAdapter(movies,this)
        recyclerView!!.adapter = itemAdapter

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
        //change back to fuel after complete
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                ShuffleFragment()
            ).commit()
            navigationView.setCheckedItem(R.id.nav_shuffle)
            navigationView.menu.getItem(1).setActionView(R.layout.menu_image)
        }
        //delete this after complete
        this@MainActivity.title = ("Balance Shuffle")
        toolbar.setBackgroundColor(getColor(R.color.colorSecondary))
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorSecondaryDark)

    }


    //clear focus when press outside textView
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    //double tab back to exit
    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                super.onBackPressed()
                return
            } else {
                Toast.makeText(baseContext, "Press back again to exit", Toast.LENGTH_SHORT).show()
            }
            backPressedTime = System.currentTimeMillis()
        }
    }
}
