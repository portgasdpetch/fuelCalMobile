package com.example.fuelCalculator

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.GestureDetector
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.abs


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var drawer: DrawerLayout
    private var backPressedTime: Long = 0.toLong()
    private lateinit var context: Context
    private lateinit var gestureDetector: GestureDetector
    private var x2:Float = 0.0f
    private var x1:Float = 0.0f
    private var y2:Float = 0.0f
    private var y1:Float = 0.0f

    companion object{
        const val MIN_DISTANCE = 150
    }

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

//        gestureDetector = GestureDetector(this,this)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)
        var navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

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
                OverviewFragment()
            ).commit()
            navigationView.setCheckedItem(R.id.nav_calculator)
            navigationView.menu.getItem(1).setActionView(R.layout.menu_image)
        }
        //delete this after complete
//        this@MainActivity.title = ("Balance Shuffle")
//        toolbar.setBackgroundColor(getColor(R.color.colorSecondary))
//        window.statusBarColor = ContextCompat.getColor(this, R.color.colorSecondaryDark)

    }

    //swiping gesture
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//
//        gestureDetector.onTouchEvent(event)
//
//        when (event?.action){
//            //when swipe is starting
//            0-> {
//                x1 = event.x
//                y1 = event.y
//            }
//
//            //when swipe is ending
//            1-> {
//                x2 = event.x
//                y2 = event.y
//
//                val valueX:Float = x2-x1
//                val valueY:Float = y2-y1
//
//                if (abs(valueX) > MIN_DISTANCE) {
//                    //left to right swipe
//                    if (x2 > x1){
//                        Toast.makeText(this,"Right Swipe",Toast.LENGTH_SHORT).show()
//                    //right to left swipe
//                    } else {
//                        Toast.makeText(this,"Left Swipe",Toast.LENGTH_SHORT).show()
//                    }
//                } else if (abs(valueY) > MIN_DISTANCE) {
//                    //detect top to bottom swipe
//                    if (y2>y1){
//                        Toast.makeText(this, "Bottom Swipe",Toast.LENGTH_SHORT).show()
//                    //bottom to top swipe
//                    } else {
//                        Toast.makeText(this,"Top Swipe",Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//
//        return super.onTouchEvent(event)
//    }


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

//    override fun onDown(p0: MotionEvent?): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun onShowPress(p0: MotionEvent?) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun onLongPress(p0: MotionEvent?) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
//        TODO("Not yet implemented")
//    }

}
