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

class ShuffleFragment : Fragment() {
    private lateinit var  nonRestriction:TextInputEditText
    private lateinit var restriction1:TextInputEditText
    private lateinit var restriction2:TextInputEditText
    private lateinit var output:TextView
    private lateinit var output2:MaterialTextView
    private lateinit var output3:TextView
    private lateinit var line:View
    private lateinit var nachosChip0:NachoTextView;private lateinit var nachosChip1:NachoTextView;private lateinit var nachosChip2:NachoTextView
    private lateinit var randomElements:List<String>
    private val numberOfElements = 2
    private var recyclerView:RecyclerView? = null
    private var randomList: ArrayList<String> = arrayListOf()
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
        nachosChip0.addChipTerminator('\n',ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL)
        nachosChip0.addChipTerminator(';',ChipTerminatorHandler.BEHAVIOR_CHIPIFY_CURRENT_TOKEN)
        nachosChip0.addChipTerminator(' ', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_TO_TERMINATOR)
//        nachosChip0.enableEditChipOnTouch(false,false)
        line.visibility = View.VISIBLE
        output = view.findViewById(R.id.output)
//        output2 = view.findViewById(R.id.output2)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(context)

        shuffleButton.setOnClickListener {
            randomList.clear()
            shufflePlayer()

            Toast.makeText(context, "Shuffled!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shufflePlayer(){
        //list of chip value
        val list = nachosChip0.chipValues;val list2 = nachosChip1.chipValues;val list3 = nachosChip2.chipValues
        var i:Int=0
        list2.shuffle()
        randomElements = list2.take(1)
        list3.shuffle()
        randomElements = list3.take(1)
        while(i<=(list.size/2)){
            randomElements = list.shuffled().take(numberOfElements).toList()
            list.removeIf { x -> randomElements.contains(x) }
            randomList.add(randomElements.toString().replace("[","").replace("]",""))
            i += 1
        }
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
        val itemAdapter = MyAdapter(randomList,requireActivity())
        recyclerView!!.adapter = itemAdapter

        output.visibility = View.VISIBLE
        output.text = list.toString().replace("[","").replace("]","")
//        output2.visibility = View.VISIBLE
//        output.text = randomElements.toString().replace("[","").replace("]","")
//        output.text = nachosChip0.text
//        output.text = nachosChip0.allChips.toString()
//        output.text = nachosChip0.chipAndTokenValues.toString()
//        output.text = nachosChip0.chipValues.toString()
    }


}








