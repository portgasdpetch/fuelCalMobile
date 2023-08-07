package com.example.fuelCalculator

import android.content.Context.WINDOW_SERVICE
import android.graphics.Bitmap
import android.graphics.Point
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment


class QrCodeGeneratorFragment : Fragment() {
    // on below line we are creating a variable
    // for our image view, edit text and a button.
    private lateinit var qrIV: ImageView
    private lateinit var msgEdt: EditText
    private lateinit var generateQRBtn: Button

    // on below line we are creating
    // a variable for bitmap
    private lateinit var bitmap: Bitmap

    // on below line we are creating
    // a variable for qr encoder.
    private lateinit var qrEncoder: QRGEncoder
    private lateinit var drawer: DrawerLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("ae","onCreateView")
        drawer = activity!!.findViewById(R.id.drawer_layout);
        val v: View = inflater.inflate(R.layout.fragment_qrcode, container, false)

        //swiping functions
        v.setOnTouchListener(object : OnSwipeTouchListener(activity) {
//            override fun onSwipeTop() {
//                Toast.makeText(activity, "Top Swipe", Toast.LENGTH_SHORT).show()
//            }

            override fun onSwipeRight() {
                drawer.openDrawer(GravityCompat.START)
            }

//            override fun onSwipeLeft() {
//                Toast.makeText(activity, "Left Swipe", Toast.LENGTH_SHORT).show()
//            }

//            override fun onSwipeBottom() {
//                Toast.makeText(activity, "Bottom Swipe", Toast.LENGTH_SHORT).show()
//            }

        })
        setHasOptionsMenu(true)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // on below line we are
        // initializing our all variables.
        qrIV = view.findViewById(R.id.idIVQrcode)
        msgEdt = view.findViewById(R.id.idEdt)
        generateQRBtn = view.findViewById(R.id.idBtnGenerateQR)

        // on below line we are adding on click
        // listener for our generate QR button.
        generateQRBtn.setOnClickListener {
            // on below line we are checking if msg edit text is empty or not.
            if (TextUtils.isEmpty(msgEdt.text.toString())) {

                // on below line we are displaying toast message to display enter some text
                Toast.makeText(activity, "Enter your message", Toast.LENGTH_SHORT).show()
            } else {
                // on below line we are getting service for window manager
                val windowManager: WindowManager = requireActivity().getSystemService(WINDOW_SERVICE) as WindowManager

                // on below line we are initializing a
                // variable for our default display
                val display: Display = windowManager.defaultDisplay

                // on below line we are creating a variable
                // for point which is use to display in qr code
                val point: Point = Point()
                display.getSize(point)

                // on below line we are getting
                // height and width of our point
                val width = point.x
                val height = point.y

                // on below line we are generating
                // dimensions for width and height
                var dimen = if (width < height) width else height
                dimen = dimen * 3 / 4

                // on below line we are initializing our qr encoder
                qrEncoder = QRGEncoder(msgEdt.text.toString(), null, QRGContents.Type.TEXT, dimen)

                // on below line we are running a try
                // and catch block for initializing our bitmap
                try {
                    // on below line we are
                    // initializing our bitmap
                    bitmap = qrEncoder.bitmap

                    // on below line we are setting
                    // this bitmap to our image view
                    qrIV.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    // on below line we
                    // are handling exception
                    e.printStackTrace()
                }
            }
        }
    }

}