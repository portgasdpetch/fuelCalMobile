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
import com.hootsuite.nachos.tokenizer.ChipTokenizer
import kotlinx.android.synthetic.main.fragment_shuffle.*
import java.util.LinkedList
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hootsuite.nachos.chip.Chip
import kotlinx.android.synthetic.main.fragment_shuffle.view.*
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
        //shuffle Non-restriction
        //if restrictions are empty
        if ((list2.size == 0) && (list3.size == 0)) {
            //round up
            val list1ceil = ceil((list1.size / numberOfElements.toDouble()))
            while (list1.size > 0) {
                list1RandomElements = list1.shuffled().take(numberOfElements)
                list1.removeIf { x -> list1RandomElements.contains(x) }
                //randomList1.add(randomElements.toString().replace("[","").replace("]",""))
                randomList.add(removeSquareBracket(list1RandomElements.toString()))
            }
        }
        // shuffle with 2 restriction
        else if ((list2.size != 0) && (list1.size != 0) && (list3.size != 0)) {
            var i: Int = 0;
            val list1Ceil = ceil(list1.size / numberOfElements.toDouble())
            val list2Ceil = ceil(list2.size / numberOfElements.toDouble())
            val list3Ceil = ceil(list3.size / numberOfElements.toDouble())
            val listCeil = list1Ceil + list2Ceil + list3Ceil
            while (list1.size != 0 || list2.size != 0 || list3.size != 0) {
                while (list1.size > 0) {
                    if (list2.size == 0 && list3.size == 0) {
                        list1RandomElements = list1.shuffled().take(numberOfElements)
                        list1.removeIf { x -> list1RandomElements.contains(x) }
                        randomList2.add(removeSquareBracket(list1RandomElements.toString()))
                        randomList.add(removeSquareBracket(randomList2.toString()))
                        randomList2.clear()
                    } else {
                        list2RandomElements = list2.shuffled().take(1)
                        list2.removeIf { x -> list2RandomElements.contains(x) }
                        randomList2.add(removeSquareBracket(list2RandomElements.toString()))
                        list3RandomElements = list3.shuffled().take(1)
                        list3.removeIf { x -> list3RandomElements.contains(x) }
                        randomList2.add(removeSquareBracket(list3RandomElements.toString()))
                        randomList.add(removeSquareBracket(randomList2.toString()))
                        randomList2.clear()
                    }
                }
            }
            //if restriction1 and non-restriction are not empty
            //shuffle with 1 restriction
        } else if ((list2.size != 0) && (list1.size != 0)) {
            var i: Int = 0;
            val list1Ceil = ceil(list1.size / numberOfElements.toDouble())
            val list2Ceil = ceil(list2.size / numberOfElements.toDouble())
            val listCeil = list1Ceil + list2Ceil

            while (list1.size != 0 || list2.size != 0) {
                if ((list1.size >= list2.size)) {
                    while (list1.size > 0) {
                        if (list2.size == 0) {
                            list1RandomElements = list1.shuffled().take(numberOfElements)
                            list1.removeIf { x -> list1RandomElements.contains(x) }
                            randomList2.add(removeSquareBracket(list1RandomElements.toString()))
                            randomList.add(removeSquareBracket(randomList2.toString()))
                            randomList2.clear()

                        } else {
                            list1RandomElements = list1.shuffled().take(numberOfElements - 1)
                            list1.removeIf { x -> list1RandomElements.contains(x) }
                            randomList2.add(removeSquareBracket(list1RandomElements.toString()))
                            list2RandomElements = list2.shuffled().take(1)
                            list2.removeIf { x -> list2RandomElements.contains(x) }
                            randomList2.add(removeSquareBracket(list2RandomElements.toString()))
                            randomList.add(removeSquareBracket(randomList2.toString()))
                            randomList2.clear()
                        }
                    }
                }
            }

        }

//        output2.text = "randomList: " + randomList


        //shuffle Restriction2
//        list3.shuffle()
//        val list3RandomElements = list3.take(1)
//        randomList3.add(list3RandomElements.toString())
//        list3.removeIf { x -> list3RandomElements.contains(x) }


//        var j:Int=0
//        while(j<=(list2.size/2)){
//            randomElements = list.shuffled().take(1).toList()
//            list.removeIf { x -> randomElements.contains(x) }
//            randomList2.add(randomElements.toString().replace("[","").replace("]",""))
//            j += 1
//        }
//        var k:Int=0
//        while(k<=(list3.size/2)){
//            randomElements = list.shuffled().take(1).toList()
//            list.removeIf { x -> randomElements.contains(x) }
//            randomList3.add(randomElements.toString().replace("[","").replace("]",""))
//            k += 1
//        }

        //add item to adapter
        val itemAdapter = MyAdapter(randomList, requireActivity())
        recyclerView!!.adapter = itemAdapter

//        output.visibility = View.VISIBLE
//        output.text = "list1: " + list1.toString().replace("[", "").replace("]", "")

//        output2.visibility = View.VISIBLE
//        output2.text = "randomList: " + removeSquareBracket(randomList.toString())
//        output3.visibility = View.VISIBLE
//        output3.text = "randomList2: " + randomList2

//        output2.visibility = View.VISIBLE
//        output.text = randomElements.toString().replace("[","").replace("]","")
//        output.text = nachosChip0.text
//        output.text = nachosChip0.allChips.toString()
//        output.text = nachosChip0.chipAndTokenValues.toString()
//        output.text = nachosChip0.chipValues.toString()
    }


}








