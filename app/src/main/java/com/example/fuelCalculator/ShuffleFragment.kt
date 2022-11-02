package com.example.fuelCalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.hootsuite.nachos.NachoTextView
import com.hootsuite.nachos.terminator.ChipTerminatorHandler
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.ceil

class ShuffleFragment : Fragment() {
    private lateinit var nonRestriction: TextInputEditText
    private lateinit var restriction1: TextInputEditText
    private lateinit var restriction2: TextInputEditText
    private lateinit var restriction0TextView: TextView
    private lateinit var restriction1TextView: TextView
    private lateinit var restriction2TextView: TextView
    private lateinit var output: TextView
    private lateinit var output2: MaterialTextView
    private lateinit var output3: TextView
    private lateinit var checkBox: CheckBox
    private lateinit var line: View
    private lateinit var nachosChip0: NachoTextView;
    private lateinit var nachosChip1: NachoTextView;
    private lateinit var nachosChip2: NachoTextView
    private lateinit var randomElements: List<String>
    private lateinit var list1RandomElements: List<String>
    private lateinit var list2RandomElements: List<String>
    private lateinit var list3RandomElements: List<String>
    private var numberOfElements = 2
    private var recyclerView: RecyclerView? = null
    private var randomList: ArrayList<String> = arrayListOf()
    private var randomList1: ArrayList<String> = arrayListOf()
    private var randomList2: ArrayList<String> = arrayListOf()
    private var randomList3: ArrayList<String> = arrayListOf()
//    private var movies = listOf("Ae1",
//        "Ae2",
//        "Ae3",
//        "Ae4",
//        "Ae8")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_shuffle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nachosChip0 = view.findViewById(R.id.restriction0NachoText)
        nachosChip1 = view.findViewById(R.id.restriction1NachoText)
        nachosChip2 = view.findViewById(R.id.restriction2NachoText)
        checkBox = view.findViewById(R.id.match_restriction_checkbox)
        val shuffleButton: Button = view.findViewById(R.id.shuffle_button)
        line = view.findViewById(R.id.view_underline)
        nachosChip0.addChipTerminator(';', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_CURRENT_TOKEN)
        nachosChip0.addChipTerminator(' ', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_TO_TERMINATOR)
//        nachosChip0.enableEditChipOnTouch(false,false)
        nachosChip1.addChipTerminator(';', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_CURRENT_TOKEN)
        nachosChip1.addChipTerminator(' ', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_TO_TERMINATOR)
        nachosChip2.addChipTerminator(';', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_CURRENT_TOKEN)
        nachosChip2.addChipTerminator(' ', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_TO_TERMINATOR)
        restriction1TextView = view.findViewById(R.id.restriction1TextView)
        restriction2TextView = view.findViewById(R.id.restriction2TextView)
//        restriction2TextView.visibility = View.GONE
//        nachosChip2.visibility = View.GONE

        line.visibility = View.VISIBLE
        output = view.findViewById(R.id.output)
        output2 = view.findViewById(R.id.output2)
        output3 = view.findViewById(R.id.output3)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(context)

//        restriction1TextView = view.findViewById(R.id.restriction1TextView)
//        restriction2TextView = view.findViewById(R.id.restriction2TextView)
//        restriction1TextView.visibility = View.GONE
//        restriction2TextView.visibility = View.GONE
//        nachosChip1.visibility = View.GONE
//        nachosChip2.visibility = View.GONE

        shuffleButton.setOnClickListener {
            randomList.clear()
            randomList1.clear()
            randomList2.clear()
            randomList3.clear()
            output.visibility = View.GONE
            shufflePlayer()

            Toast.makeText(context, "Shuffled!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeSquareBracket(x: String): String {
        return x.replace("[", "").replace("]", "")
    }

    private fun shufflePlayer() {
        //list of chip value
        val list1 = nachosChip0.chipValues
        val list2 = nachosChip1.chipValues
        val list3 = nachosChip2.chipValues

        //2 restrictions
        //matches restrictions first
        if (checkBox.isChecked) {
            //while chips are not empty
            while (list1.size > 0 || list2.size > 0 || list3.size > 0) {
                //matches list2 and 3
                if ((list2.size != 0 && list3.size != 0)) {
                    list2RandomElements = list2.shuffled().take(1)
                    list2.removeIf { x -> list2RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list2RandomElements.toString()))
                    list3RandomElements = list3.shuffled().take(1)
                    list3.removeIf { x -> list3RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list3RandomElements.toString()))
                    randomList.add(removeSquareBracket(randomList2.toString()))
                    randomList2.clear()


                    //list 1 and list 2 are not empty, list 3 is
                } else if (list3.size == 0 && list2.size != 0 && list1.size != 0) {
                    list2RandomElements = list2.shuffled().take(1)
                    list2.removeIf { x -> list2RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list2RandomElements.toString()))
                    list1RandomElements = list1.shuffled().take(numberOfElements - 1)
                    list1.removeIf { x -> list1RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list1RandomElements.toString()))
                    randomList.add(removeSquareBracket(randomList2.toString()))
                    randomList2.clear()

                    //list 1 and 3 are not empty, list 2 is
                } else if (list2.size == 0 && list1.size != 0 && list3.size != 0) {
                    list1RandomElements = list1.shuffled().take(numberOfElements - 1)
                    list1.removeIf { x -> list1RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list1RandomElements.toString()))
                    list3RandomElements = list3.shuffled().take(1)
                    list3.removeIf { x -> list3RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list3RandomElements.toString()))
                    randomList.add(removeSquareBracket(randomList2.toString()))
                    randomList2.clear()

                    //only non-restriction is left
                } else if (list1.size > 0) {
                    list1RandomElements = list1.shuffled().take(numberOfElements)
                    list1.removeIf { x -> list1RandomElements.contains(x) }
                    randomList.add(removeSquareBracket(list1RandomElements.toString()))
                }
            }//after while loop (chips are empty)
            if (randomList2.size != 0) {
                output.visibility = View.VISIBLE
                output.text = removeSquareBracket(randomList2.toString())
            }

        // with all randomly
        } else {
            // shuffle with 2 restriction
            while (list1.size > 0 || list2.size > 0 || list3.size > 0) {
                //all are equal
                if (list1.size == list2.size && list1.size == list3.size) {
                    list1RandomElements = list1.shuffled().take(1)
                    list1.removeIf { x -> list1RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list1RandomElements.toString()))
                    list2RandomElements = list2.shuffled().take(1)
                    list2.removeIf { x -> list2RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list2RandomElements.toString()))
                    list3RandomElements = list3.shuffled().take(1)
                    list3.removeIf { x -> list3RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list3RandomElements.toString()))
                    randomList1.add(removeSquareBracket(randomList2.shuffled().take(numberOfElements).toString()))
                    randomList2.removeIf { x -> randomList1.contains(x) }
                    randomList.add(removeSquareBracket(randomList1.toString()))
                    randomList1.clear()
//                    output2.visibility = View.VISIBLE
//                    output2.text = "RandomList1 :$randomList1"


                } else if (list1.size >= list2.size && list2.size > list3.size){
                    list1RandomElements = list1.shuffled().take(1)
                    list1.removeIf { x -> list1RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list1RandomElements.toString()))
                    list2RandomElements = list2.shuffled().take(1)
                    list2.removeIf { x -> list2RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list2RandomElements.toString()))
                    randomList1.add(removeSquareBracket(randomList2.toString()))
                    randomList2.removeIf { x -> randomList1.contains(x) }
                    randomList.add(removeSquareBracket(randomList1.toString()))
                    randomList1.clear()


                } else if (list1.size>=list3.size && list3.size > list2.size){
                    list1RandomElements = list1.shuffled().take(1)
                    list1.removeIf { x -> list1RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list1RandomElements.toString()))
                    list3RandomElements = list3.shuffled().take(1)
                    list3.removeIf { x -> list3RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list3RandomElements.toString()))
                    randomList1.add(removeSquareBracket(randomList2.toString()))
                    randomList2.removeIf { x -> randomList1.contains(x) }
                    randomList.add(removeSquareBracket(randomList1.toString()))
                    randomList1.clear()


                } else if (list1.size > 0 && list3.size == 0 && list2.size == 0) {
                    list1RandomElements = list1.shuffled().take(numberOfElements)
                    list1.removeIf { x -> list1RandomElements.contains(x) }
                    randomList.add(removeSquareBracket(list1RandomElements.toString()))
                }


            }//when chips are empty
            if (randomList2.size != 0) {
                output.visibility = View.VISIBLE
                output.text = "randomList2: "+removeSquareBracket(randomList2.toString())
            }
        }


        //add item to adapter
        val itemAdapter = MyAdapter(randomList, requireActivity())
        recyclerView!!.adapter = itemAdapter
    }
}








