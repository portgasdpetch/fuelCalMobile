package com.example.fuelCalculator

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_shuffle.*

class OilPriceFragment : Fragment() {
    private lateinit var textView: TextView
    private lateinit var drawer: DrawerLayout
    private lateinit var oilPriceRecycler: RecyclerView
    private lateinit var oilPriceText: TextView
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        drawer = requireActivity().findViewById(R.id.drawer_layout);
        val v: View = inflater.inflate(R.layout.fragment_oilprice, container, false)
        setHasOptionsMenu(true)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        oilPriceText = view.findViewById(R.id.oilPriceText)
        val pressButton: Button = view.findViewById(R.id.fireBaseButton)

        pressButton.setOnClickListener {
            onPress()
        }
    }

    private fun onPress() {
        database = Firebase.database.reference

//        database.child("accountName").child("test").removeValue()
//        Toast.makeText(context,"Yeah",Toast.LENGTH_SHORT).show()

//        val postListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // Get Post object and use the values to update the UI
//                val post = dataSnapshot.value
//                oilPriceText.visibility = View.VISIBLE
//                oilPriceText.text = post.toString()
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
//            }
//        }
//        postReference.addValueEventListener(postListener)

        val petch = database.child("accountName").child("petch").get().addOnSuccessListener {
            val petch = it.child("!fullName").value
            oilPriceText.visibility = View.VISIBLE
            Log.d("firebase", petch.toString())
            oilPriceText.text = petch.toString()
        }.addOnFailureListener {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            oilPriceText.visibility = View.INVISIBLE
        }
    }
}