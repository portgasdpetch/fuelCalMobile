package com.example.fuelCalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.hootsuite.nachos.NachoTextView
import com.hootsuite.nachos.terminator.ChipTerminatorHandler


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
    private lateinit var output4: TextView
    private lateinit var output5: TextView
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

    private lateinit var drawer: DrawerLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        drawer = activity!!.findViewById(R.id.drawer_layout);
        val v: View = inflater.inflate(R.layout.fragment_shuffle, container, false)

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

        var suggestions =
            arrayOf("Petch", "Tar", "Toy", "Que", "Mon", "Boat", "Ton", "Wan", "Bill", "Jame", "Moow", "Kerz", "Pao", "Win")
        var adapter: ArrayAdapter<String> =
            ArrayAdapter(requireActivity(),android.R.layout.simple_dropdown_item_1line, suggestions)

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
        nachosChip0.setAdapter(adapter)
        nachosChip1.setAdapter(adapter)
        nachosChip2.setAdapter(adapter)
        restriction1TextView = view.findViewById(R.id.restriction1TextView)
        restriction2TextView = view.findViewById(R.id.restriction2TextView)
//        restriction2TextView.visibility = View.GONE
//        nachosChip2.visibility = View.GONE

        line.visibility = View.VISIBLE
        output = view.findViewById(R.id.output)
        output2 = view.findViewById(R.id.output2)
        output3 = view.findViewById(R.id.output3)
        output4 = view.findViewById(R.id.output4)
        output5 = view.findViewById(R.id.output5)
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

    private fun removeComma(x: String): String {
        return x.replace(",", "")
    }

//    override fun onStop() {
//        super.onStop()
//        activity!!.finish()
//    }

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

//            for debug
//            if (list1.size + list2.size <= list3.size) {
//                list2RandomElements = list2.shuffled().take(1)
//                list2.removeIf { x -> list2RandomElements.contains(x) }
//                randomList2.add(removeSquareBracket(list2RandomElements.toString()))
//                list1RandomElements = list1.shuffled().take(1)
//                list1.removeIf { x -> list1RandomElements.contains(x) }
//                randomList2.add(removeSquareBracket(list1RandomElements.toString()))
//                randomList1 = ArrayList(randomList2.shuffled().take(1))
//                randomList2.removeIf { x -> randomList1.contains(x) }
//                randomList3.add(removeSquareBracket(randomList1.toString()))
//                randomList1.clear()
//                //return shuffled value to listX
//                when {
//                    nachosChip0.chipValues.containsAll(randomList2) -> {
//                        list1.addAll(randomList2)
//                    }
//                    nachosChip1.chipValues.containsAll(randomList2) -> {
//                        list2.addAll(randomList2)
//                    }
//                    nachosChip2.chipValues.containsAll(randomList2) -> {
//                        list3.addAll(randomList2)
//                    }
//                }
//                list3RandomElements = list3.shuffled().take(1)
//                list3.removeIf { x -> list3RandomElements.contains(x) }
//                randomList3.add(removeSquareBracket(list3RandomElements.toString()))
//                randomList.add(removeSquareBracket(randomList3.toString()))
//                randomList2.clear()
//            }


//                output.visibility = View.VISIBLE
//                output2.visibility = View.VISIBLE
//                output3.visibility = View.VISIBLE
//                output4.visibility = View.VISIBLE
//                output5.visibility = View.VISIBLE
//                output.text = "list1: "+list1
//                output2.text = "list2: "+list2
//                output3.text = "list3: $list3"
//                output4.text = "randomList1: $randomList1"
//                output5.text = "randomList2: $randomList2"
//            }

//             shuffle with 2 restriction
            while (list1.size > 0 || list2.size > 0 || list3.size > 0) {

                //all are equal or none are 0
//                if list 1 is empty
                if (list1.isEmpty() && list2.isNotEmpty() && list3.isNotEmpty() && ((list2.size-list3.size < numberOfElements) || (list3.size-list2.size < numberOfElements))) {
                    list2RandomElements = list2.shuffled().take(1)
                    list2.removeIf { x -> list2RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list2RandomElements.toString()))
                    list3RandomElements = list3.shuffled().take(1)
                    list3.removeIf { x -> list3RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list3RandomElements.toString()))
                    randomList.add(removeSquareBracket(randomList2.toString()))
                    randomList2.clear()
                }
//                if list2 is empty
                else if (list2.isEmpty() && list1.isNotEmpty() && list3.isNotEmpty()) {
                    list1RandomElements = list1.shuffled().take(1)
                    list1.removeIf { x -> list1RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list1RandomElements.toString()))
                    list3RandomElements = list3.shuffled().take(1)
                    list3.removeIf { x -> list3RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list3RandomElements.toString()))
                    randomList.add(removeSquareBracket(randomList2.toString()))
                    randomList2.clear()

                    //if list3 is empty
                } else if (list3.isEmpty() && list1.isNotEmpty() && list2.isNotEmpty()) {
                    list1RandomElements = list1.shuffled().take(1)
                    list1.removeIf { x -> list1RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list1RandomElements.toString()))
                    list2RandomElements = list2.shuffled().take(1)
                    list2.removeIf { x -> list2RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list2RandomElements.toString()))
                    randomList.add(removeSquareBracket(randomList2.toString()))
                    randomList2.clear()

                    //if list2 and 3 are empty
                } else if (list2.isEmpty() && list3.isEmpty()) {
                    list1RandomElements = list1.shuffled().take(numberOfElements)
                    list1.removeIf { x -> list1RandomElements.contains(x) }
                    randomList.add(removeSquareBracket(list1RandomElements.toString()))

                    //1 and 3 are combined and equal 2 or 1 and 2 are combined and equal 3
                } else if ((list1.size + list3.size <= list2.size)) {
                    list3RandomElements = list3.shuffled().take(1)
                    list3.removeIf { x -> list3RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list3RandomElements.toString()))
                    list1RandomElements = list1.shuffled().take(1)
                    list1.removeIf { x -> list1RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list1RandomElements.toString()))
                    randomList1 = ArrayList(randomList2.shuffled().take(1))
                    randomList2.removeIf { x -> randomList1.contains(x) }
                    randomList3.add(removeSquareBracket(randomList1.toString()))
                    randomList1.clear()
                    //return shuffled value to listX
                    when {
                        nachosChip0.chipValues.containsAll(randomList2) -> {
                            list1.addAll(randomList2)
                        }
                        nachosChip1.chipValues.containsAll(randomList2) -> {
                            list2.addAll(randomList2)
                        }
                        nachosChip2.chipValues.containsAll(randomList2) -> {
                            list3.addAll(randomList2)
                        }
                    }

                    list2RandomElements = list2.shuffled().take(1)
                    list2.removeIf { x -> list2RandomElements.contains(x) }
                    randomList3.add(removeSquareBracket(list2RandomElements.toString()))
                    randomList.add(removeSquareBracket(randomList3.toString()))
                    randomList2.clear()
                    randomList3.clear()

                } else if (list1.size + list2.size <= list3.size) {
                    list2RandomElements = list2.shuffled().take(1)
                    list2.removeIf { x -> list2RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list2RandomElements.toString()))
                    list1RandomElements = list1.shuffled().take(1)
                    list1.removeIf { x -> list1RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list1RandomElements.toString()))
                    randomList1 = ArrayList(randomList2.shuffled().take(1))
                    randomList2.removeIf { x -> randomList1.contains(x) }
                    randomList3.add(removeSquareBracket(randomList1.toString()))
                    randomList1.clear()
                    //return shuffled value to listX
                    when {
                        nachosChip0.chipValues.containsAll(randomList2) -> {
                            list1.addAll(randomList2)
                        }
                        nachosChip1.chipValues.containsAll(randomList2) -> {
                            list2.addAll(randomList2)
                        }
                        nachosChip2.chipValues.containsAll(randomList2) -> {
                            list3.addAll(randomList2)
                        }
                    }
                    list3RandomElements = list3.shuffled().take(1)
                    list3.removeIf { x -> list3RandomElements.contains(x) }
                    randomList3.add(removeSquareBracket(list3RandomElements.toString()))
                    randomList.add(removeSquareBracket(randomList3.toString()))
                    randomList2.clear()
                    randomList3.clear()
                }
                else {
                    list1RandomElements = list1.shuffled().take(1)
                    list1.removeIf { x -> list1RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list1RandomElements.toString()))
                    list2RandomElements = list2.shuffled().take(1)
                    list2.removeIf { x -> list2RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list2RandomElements.toString()))
                    list3RandomElements = list3.shuffled().take(1)
                    list3.removeIf { x -> list3RandomElements.contains(x) }
                    randomList2.add(removeSquareBracket(list3RandomElements.toString()))
                    randomList1 = randomList2.shuffled().take(numberOfElements) as ArrayList<String>
                    randomList2.removeIf { x -> randomList1.contains(x) }
                    randomList.add(removeSquareBracket(randomList1.toString()))
                    randomList1.clear()

                    //return shuffled value to listX
                    when {
                        nachosChip0.chipValues.containsAll(randomList2) -> {
                            list1.addAll(randomList2)
                        }
                        nachosChip1.chipValues.containsAll(randomList2) -> {
                            list2.addAll(randomList2)
                        }
                        nachosChip2.chipValues.containsAll(randomList2) -> {
                            list3.addAll(randomList2)
                        }
                    }


                    //clear randomList2 after added
                    randomList2.clear()


                }

//            else if (list1.size == 1) {
//                    if (list1.size + list2.size <= list3.size) {
//                        list2RandomElements = list2.shuffled().take(1)
//                        list2.removeIf { x -> list2RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list2RandomElements.toString()))
//                    }
//                }


            }
//            //when chips are empty
////            if (randomList3.size == numberOfElements) {
////                randomList.add(removeSquareBracket(randomList3.toString()))
////                randomList3.clear()
////
////            }

//            if (randomList2.size != 0) {
//                output2.visibility = View.VISIBLE
//                output2.text = "randomList2: " + removeSquareBracket(randomList2.toString())
//                output3.visibility = View.VISIBLE
//                output3.text = "randomList2.size : " + randomList2.size
//            }

//            output.visibility = View.VISIBLE
//            output.text = "randomList2: " + randomList2
        }


        //add item to adapter
        val itemAdapter = MyAdapter(randomList, requireActivity())
        recyclerView!!.adapter = itemAdapter
    }

}








