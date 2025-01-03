//package com.example.fuelCalculator
//
//import android.content.Context
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import androidx.core.view.GravityCompat
//import androidx.drawerlayout.widget.DrawerLayout
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.android.material.chip.Chip
//import com.google.android.material.chip.ChipGroup
//import com.google.android.material.textfield.TextInputEditText
//import com.google.android.material.textview.MaterialTextView
//import com.hootsuite.nachos.NachoTextView
//import com.hootsuite.nachos.terminator.ChipTerminatorHandler
//
//
//class ShuffleFragment : Fragment() {
//    private lateinit var nonRestriction: TextInputEditText
//    private lateinit var restriction1: TextInputEditText
//    private lateinit var restriction2: TextInputEditText
//    private lateinit var restriction0TextView: TextView
//    private lateinit var restriction1TextView: TextView
//    private lateinit var restriction2TextView: TextView
//    private lateinit var output: TextView
//    private lateinit var output2: MaterialTextView
//    private lateinit var output3: TextView
//    private lateinit var output4: TextView
//    private lateinit var output5: TextView
//    private lateinit var restrictionCheckBox: CheckBox
//    private lateinit var noDuplicateCheckBox: CheckBox
//    private lateinit var line: View
//    private lateinit var nachosChip0: NachoTextView;
//    private lateinit var nachosChip1: NachoTextView;
//    private lateinit var nachosChip2: NachoTextView
//    private lateinit var randomElements: List<String>
//    private lateinit var list1RandomElements: List<String>
//    private lateinit var list2RandomElements: List<String>
//    private lateinit var list3RandomElements: List<String>
//    private var randomListForNonDuplicateElements: ArrayList<String> = arrayListOf()
//    private var randomList1ForNonDuplicateElements: ArrayList<String> = arrayListOf()
//    private var randomList2ForNonDuplicateElements: ArrayList<String> = arrayListOf()
//    private var randomList3ForNonDuplicateElements: ArrayList<String> = arrayListOf()
//    private var numberOfDesiredElement:Int = 0
//    private var recyclerView: RecyclerView? = null
//    private var randomList: ArrayList<String> = arrayListOf()
//    private var randomList1: ArrayList<String> = arrayListOf()
//    private var randomList2: ArrayList<String> = arrayListOf()
//    private var randomList3: ArrayList<String> = arrayListOf()
//    private lateinit var destroyedString:String
//    private lateinit var playerQuantityEditText:EditText
//    private var counter = 1
//    private lateinit var chip0: Chip
//    private lateinit var chipGroup: ChipGroup
//    private lateinit var editText0: EditText
////    private lateinit var editText: EditText
////    private lateinit var chipRecyclerView: RecyclerView
//    private lateinit var chipAdapter: ChipAdapter
//    private val chips = mutableListOf<String>()
//    private var chipId:Int=0
//
//    private lateinit var preferences: SharedPreferences
//
//    private lateinit var drawer: DrawerLayout
//
//    private fun getChipId(): Int {
//        return chipId
//    }
//    private fun setChipId(chipId:Int) {
//            this.chipId = chipId
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        drawer = activity!!.findViewById(R.id.drawer_layout);
//        val v: View = inflater.inflate(R.layout.fragment_shuffle, container, false)
//
//        //swiping functions
//        v.setOnTouchListener(object : OnSwipeTouchListener(activity) {
////            override fun onSwipeTop() {
////                Toast.makeText(activity, "Top Swipe", Toast.LENGTH_SHORT).show()
////            }
//
//            override fun onSwipeRight() {
//                drawer.openDrawer(GravityCompat.START)
//            }
//
////            override fun onSwipeLeft() {
////                Toast.makeText(activity, "Left Swipe", Toast.LENGTH_SHORT).show()
////            }
//
////            override fun onSwipeBottom() {
////                Toast.makeText(activity, "Bottom Swipe", Toast.LENGTH_SHORT).show()
////            }
//        })
//
//        setHasOptionsMenu(true)
//        return v
//    }
//
//    private fun handleSpaceBar() {
//        counter++
//        val runningNumber = "$counter.${nachosChip0.text}"
//        nachosChip0.setText(runningNumber)
////        nachosChip0.setSelection(runningNumber.length)
//    }
//
//    private fun addChip(chipText: String) {
//        chips.add(chipText)
//        chipAdapter.notifyItemInserted(chips.size - 1)
//    }
//
//    private fun createChip(chipText: String) {
//        counter++
//        val chip = Chip(chipGroup.context)
//        chip.text = chipText
//        chip.id = View.generateViewId()
//        setChipId(chip.id)
//        chip.isCloseIconVisible = true
//        chip.setOnCloseIconClickListener {
//            chipGroup.removeView(chip)
//            counter--
//        }
//        Log.d("id","${getChipId()}")
//        chipGroup.addView(chip)
//    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        var suggestions =
//            arrayOf("Petch", "Tar", "Que", "Mon", "Ton", "Bill", "Jame", "Moow", "Pao", "Win","Jedi","Ohm","Jeff","Jumpoon","Tae","Moss")
//        var adapter: ArrayAdapter<String> =
//            ArrayAdapter(requireActivity(),android.R.layout.simple_dropdown_item_1line, suggestions)
//
//        nachosChip0 = view.findViewById(R.id.restriction0NachoText)
//        nachosChip1 = view.findViewById(R.id.restriction1NachoText)
//        nachosChip2 = view.findViewById(R.id.restriction2NachoText)
//        restrictionCheckBox = view.findViewById(R.id.match_restriction_checkbox)
//        noDuplicateCheckBox = view.findViewById(R.id.no_duplicate_checkbox)
//        val shuffleButton: Button = view.findViewById(R.id.shuffle_button)
//        val resetButton: Button = view.findViewById(R.id.reset_button)
//        line = view.findViewById(R.id.view_underline)
//        nachosChip0.addChipTerminator(';', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_CURRENT_TOKEN)
//        nachosChip0.addChipTerminator(' ', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_TO_TERMINATOR)
//        nachosChip0.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);
////        nachosChip0.enableEditChipOnTouch(false,false)
//        nachosChip1.addChipTerminator(';', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_CURRENT_TOKEN)
//        nachosChip1.addChipTerminator(' ', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_TO_TERMINATOR)
//        nachosChip1.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);
//
//        nachosChip2.addChipTerminator(';', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_CURRENT_TOKEN)
//        nachosChip2.addChipTerminator(' ', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_TO_TERMINATOR)
//        nachosChip2.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);
//
//        //deving
//        nachosChip0.setOnFocusChangeListener { _, hasFocus ->
//            if (hasFocus) {
//                nachosChip0.setText("Petch Pao Tar Que Bill Mon ")
//            }
//        }
//
//        noDuplicateCheckBox.setOnCheckedChangeListener { _, _ ->
//            // Handle checkbox state change here
//            if (noDuplicateCheckBox.isChecked) {
//                restrictionCheckBox.isChecked = false
//                playerQuantityEditText.setText("2")
//            }
//        }
//        //deving
//        val regex = Regex("\\d")
//        val chip0ContainsNumber = regex.containsMatchIn(nachosChip0.text)
//
////        nachosChip0.addTextChangedListener(object : TextWatcher {
////            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
////                // TODO Auto-generated method stub
//////                if (nachosChip0.tokenValues.size>0) {
//////                    Log.d("length", "length: "+nachosChip0.tokenValues[0].length.toString())
//////                }
////////                if (s != null && s.isNotEmpty() && s.last() == ' ') {
//////                if (s != null && s.isNotEmpty() && nachosChip0.tokenValues.size>0 && nachosChip0.tokenValues[0].length==1) {
//////                    counter++
//////                    var chipValue: MutableList<String> = nachosChip0.chipValues
////////                    Log.d("value","nachosChipValues: "+nachosChip0.chipValues)
//////                    chipValue.replaceAll {it.replace("[","")}
//////                    chipValue.replaceAll {it.replace("]","")}
//////                    chipValue.replaceAll {it.replace(",","")}
//////                    var stringChipValue = chipValue.toString().replace("[","")
//////                    stringChipValue = stringChipValue.replace("]","")
//////                    stringChipValue = stringChipValue.replace(",","");
////////                    Log.d("value", "StringChipValue: $stringChipValue")
////////                    nachosChip0.append("${counter}.")
//////                    nachosChip0.text.insert(nachosChip0.selectionStart, "$counter.")
////////                    nachosChip0.setSelection(nachosChip0.length())
//////                    true
//////                } else {
//////                    false
//////                }
////            }
////
////            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
////                // TODO Auto-generated method stub
////            }
////
////            override fun afterTextChanged(s: Editable) {
////                // TODO Auto-generated method stub
////                Log.d("length","chipValueSize: "+nachosChip0.chipValues.size.toString())
////                Log.d("length","chip0TextLength: "+nachosChip0.text.length.toString())
////                if (nachosChip0.tokenValues.size>0) {
////                    Log.d("length", "length: "+nachosChip0.tokenValues[0].length.toString())
////                }
////                if (s != null && s.isNotEmpty() && s.last() == ' ') {
//////                if (s != null && s.isNotEmpty() && nachosChip0.tokenValues.size>0 && nachosChip0.tokenValues[0].length==1) {
////                    counter++
////                    var chipValue: MutableList<String> = nachosChip0.chipValues
//////                    Log.d("value","nachosChipValues: "+nachosChip0.chipValues)
////                    chipValue.replaceAll {it.replace("[","")}
////                    chipValue.replaceAll {it.replace("]","")}
////                    chipValue.replaceAll {it.replace(",","")}
////                    var stringChipValue = chipValue.toString().replace("[","")
////                    stringChipValue = stringChipValue.replace("]","")
////                    stringChipValue = stringChipValue.replace(",","");
//////                    Log.d("value", "StringChipValue: $stringChipValue")
////                    nachosChip0.append("${counter}.")
//////                    nachosChip0.text.insert(nachosChip0.selectionStart, "$counter.")
//////                    nachosChip0.text.replace(nachosChip0.selectionStart-1,nachosChip0.selectionStart,s.last().toString())
//////                    nachosChip0.text = nachosChip0.text
//////                    nachosChip0.setSelection(nachosChip0.length())
////                    true
////                } else {
////                    false
////                }
////            }
////        })
//
//        //in case no chip added yet
////        nachosChip0.setOnFocusChangeListener{ _, hasFocus ->
////            if (hasFocus) {
////                if(nachosChip0.tokenValues.isEmpty() && nachosChip1.tokenValues.isEmpty() && nachosChip2.tokenValues.isEmpty()){
////                    nachosChip0.setText("${counter}.")
////                }
////            } else {
////                if(nachosChip0.chipValues.isEmpty() && nachosChip1.chipValues.isEmpty() && nachosChip2.chipValues.isEmpty()){
////                    nachosChip0.setText("")
////                }
////                else if (nachosChip0.chipValues.isNotEmpty() && nachosChip1.chipValues.isEmpty() && nachosChip2.chipValues.isEmpty()){
////                    var chipValue: MutableList<String> = nachosChip0.chipValues
////                    Log.d("value","nachosChipValues: "+nachosChip0.chipValues)
////                    chipValue.replaceAll {it.replace("[","")}
////                    chipValue.replaceAll {it.replace("]","")}
////                    chipValue.replaceAll {it.replace(",","")}
////                    var stringChipValue = chipValue.toString().replace("[","")
////                    stringChipValue = stringChipValue.replace("]","")
////                    stringChipValue = stringChipValue.replace(",","");
//////                    var text = nachosChip0.text.toString()
//////                    nachosChip0.text = nachosChip0.text.delete(nachosChip0.text.length-2,nachosChip0.text.length)
//////                    nachosChip0.setText(nachosChip0.text)
////                    nachosChip0.setText(stringChipValue+" ")
////                }
////            }
////        }
////        nachosChip1.setOnFocusChangeListener{ _, hasFocus ->
////            if (hasFocus) {
////                if(nachosChip0.tokenValues.isEmpty() && nachosChip1.tokenValues.isEmpty() && nachosChip2.tokenValues.isEmpty()){
////                    nachosChip1.setText("${counter}.")
////                }
////            } else {
////                if(nachosChip0.chipValues.isEmpty() && nachosChip1.chipValues.isEmpty() && nachosChip2.chipValues.isEmpty()){
////                    nachosChip1.setText("")
////                }
////            }
////        }
////        nachosChip2.setOnFocusChangeListener{ _, hasFocus ->
////            if (hasFocus) {
////                if(nachosChip0.tokenValues.isEmpty() && nachosChip1.tokenValues.isEmpty() && nachosChip2.tokenValues.isEmpty()){
////                    nachosChip2.setText("${counter}.")
////                }
////            } else {
////                if(nachosChip0.chipValues.isEmpty() && nachosChip1.chipValues.isEmpty() && nachosChip2.chipValues.isEmpty()){
////                    nachosChip2.setText("")
////                }
////            }
////        }
//
//        //start working for physical keyboard
////        nachosChip0.setOnFocusChangeListener{ _, hasFocus ->
////            if (hasFocus) {
////                if(nachosChip0.text.isBlank() && nachosChip1.text.isBlank() && nachosChip2.text.isBlank()){
////                    nachosChip0.setText("${counter}.")
////                }
////            } else {
////
////            }
////        }
////        nachosChip0.requestFocus()
////        nachosChip0.setOnKeyListener { _, keyCode, event ->
////            if (keyCode == KeyEvent.KEYCODE_SPACE && event.action == KeyEvent.ACTION_UP) {
////                counter++
////                var chipValue: MutableList<String> = nachosChip0.chipValues
////                chipValue.replaceAll {it.replace("[","")}
////                chipValue.replaceAll {it.replace("]","")}
////                chipValue.replaceAll {it.replace(",","")}
////                var stringChipValue = chipValue.toString().replace("[","")
////                stringChipValue = stringChipValue.replace("]","")
////                stringChipValue = stringChipValue.replace(",","");
////                Log.d("value",stringChipValue)
////                nachosChip0.setText("$stringChipValue ${counter}.")
////                nachosChip0.setSelection(nachosChip0.length())
////                true
////            } else {
////                false
////            }
////        }
//        //end working
//
//
//        //adding running number via editText
////        chipGroup = view.findViewById(R.id.restriction0ChipGroup)
////        editText0 = view.findViewById(R.id.restriction0EditText)
////        editText0.setText("${++counter}.")
////        editText0.requestFocus()
////        val chipSpaceTextWatcher = ChipSpaceTextWatcher(chipGroup, editText0)
////        editText0.addTextChangedListener(chipSpaceTextWatcher)
//
////        editText0.setOnKeyListener { _, keyCode, event ->
////            if (keyCode == KeyEvent.KEYCODE_SPACE && event.action == KeyEvent.ACTION_UP) {
////                chipSpaceTextWatcher.handleSpacePressed(editText0.text)
////                editText0.text.clear()
////                true
////            } else {
////                false
////            }
////        }
//
//        //adding running number via RecyclerView
////        editText = view.findViewById(R.id.editText)
////        chipRecyclerView = view.findViewById(R.id.chipRecyclerView)
////        chipAdapter = ChipAdapter(chips)
////        chipRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
////        chipRecyclerView.adapter = chipAdapter
////        editText.setOnKeyListener { _, keyCode, event ->
////            if (keyCode == KeyEvent.KEYCODE_SPACE && event.action == KeyEvent.ACTION_UP) {
////                val chipText = editText.text.toString().trim()
////                if (chipText.isNotEmpty()) {
////                    addChip(chipText)
////                    editText.setText("")
////                }
////                true
////            } else {
////                false
////            }
////        }
//
//
//        nachosChip0.setAdapter(adapter)
//        nachosChip1.setAdapter(adapter)
//        nachosChip2.setAdapter(adapter)
//        restriction1TextView = view.findViewById(R.id.restriction1TextView)
//        restriction2TextView = view.findViewById(R.id.restriction2TextView)
////        restriction2TextView.visibility = View.GONE
////        nachosChip2.visibility = View.GONE
//        output = view.findViewById(R.id.output)
//        output2 = view.findViewById(R.id.output2)
//        output3 = view.findViewById(R.id.output3)
//        output4 = view.findViewById(R.id.output4)
//        output5 = view.findViewById(R.id.output5)
//
//
//        //last
////        editText0.setOnKeyListener { _, keyCode, event ->
////            if (keyCode == KeyEvent.KEYCODE_SPACE && event.action == KeyEvent.ACTION_UP) {
////                val chipText = editText0.text.trim().toString()
////                if (chipText.isNotBlank()) {
////                    createChip(chipText)
////                    //clear after created
//////                    editText0.text.clear()
////                    //adding prefix number
////                    editText0.setText("$counter.")
////                    //move cursor to the end of number
////                    editText0.setSelection(editText0.length())
////                    val editText0LayoutParams:RelativeLayout.LayoutParams = editText0.layoutParams as RelativeLayout.LayoutParams
////                    editText0LayoutParams.addRule(RelativeLayout.END_OF,chipGroup.id)
////                    editText0.layoutParams = editText0LayoutParams
////                    val restriction1TextViewLayoutParams:RelativeLayout.LayoutParams = restriction1TextView.layoutParams as RelativeLayout.LayoutParams
////                    restriction1TextViewLayoutParams.addRule(RelativeLayout.BELOW,chipGroup.id)
////                    restriction1TextView.layoutParams = restriction1TextViewLayoutParams
////                }
////                true
////            } else {
////                false
////            }
////        }
//
//        //restriction1
////        nachosChip1.addTextChangedListener(object : TextWatcher {
////            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
////                // TODO Auto-generated method stub
////            }
////
////            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
////                // TODO Auto-generated method stub
////
////            }
////
////            override fun afterTextChanged(s: Editable) {
////                // TODO Auto-generated method stub
////                if (s != null && s.isNotEmpty() && s.last() == ' ') {
////                    counter++
////                    var chipValue: MutableList<String> = nachosChip1.chipValues
//////                    Log.d("value","nachosChipValues: "+nachosChip0.chipValues)
////                    chipValue.replaceAll {it.replace("[","")}
////                    chipValue.replaceAll {it.replace("]","")}
////                    chipValue.replaceAll {it.replace(",","")}
////                    var stringChipValue = chipValue.toString().replace("[","")
////                    stringChipValue = stringChipValue.replace("]","")
////                    stringChipValue = stringChipValue.replace(",","");
//////                    Log.d("value", "StringChipValue: $stringChipValue")
////                    nachosChip1.append("${counter}.")
//////                    nachosChip0.setSelection(nachosChip0.length())
////                    true
////                } else {
////                    false
////                }
////            }
////        })
////        nachosChip1.setOnKeyListener { _, keyCode, event ->
////            if (keyCode == KeyEvent.KEYCODE_SPACE && event.action == KeyEvent.ACTION_UP) {
////                counter++
////                var chipValue: MutableList<String> = nachosChip1.chipValues
////                chipValue.replaceAll {it.replace("[","")}
////                chipValue.replaceAll {it.replace("]","")}
////                chipValue.replaceAll {it.replace(",","")}
////                var stringChipValue = chipValue.toString().replace("[","")
////                stringChipValue = stringChipValue.replace("]","")
////                stringChipValue = stringChipValue.replace(",","");
////                Log.d("value",stringChipValue)
////                nachosChip1.setText("$stringChipValue ${counter}.")
////                nachosChip1.setSelection(nachosChip1.length())
////                true
////            } else {
////                false
////            }
////        }
//
//        //restriction2
////        nachosChip2.addTextChangedListener(object : TextWatcher {
////            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
////                // TODO Auto-generated method stub
////            }
////
////            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
////                // TODO Auto-generated method stub
////
////            }
////
////            override fun afterTextChanged(s: Editable) {
////                // TODO Auto-generated method stub
////                if (s != null && s.isNotEmpty() && s.last() == ' ') {
////                    counter++
////                    var chipValue: MutableList<String> = nachosChip2.chipValues
//////                    Log.d("value","nachosChipValues: "+nachosChip0.chipValues)
////                    chipValue.replaceAll {it.replace("[","")}
////                    chipValue.replaceAll {it.replace("]","")}
////                    chipValue.replaceAll {it.replace(",","")}
////                    var stringChipValue = chipValue.toString().replace("[","")
////                    stringChipValue = stringChipValue.replace("]","")
////                    stringChipValue = stringChipValue.replace(",","");
//////                    Log.d("value", "StringChipValue: $stringChipValue")
////                    nachosChip2.append("${counter}.")
//////                    nachosChip0.setSelection(nachosChip0.length())
////                    true
////                } else {
////                    false
////                }
////            }
////        })
//
//        playerQuantityEditText = view.findViewById(R.id.playerQuantityEditText)
//
//        preferences = activity!!.getSharedPreferences("SHUFFLING_PREFERENCE", Context.MODE_PRIVATE)
//
//        nachosChip0.setText(preferences.getString("Chunin",""))
//        nachosChip1.setText(preferences.getString("Jounin",""))
//        nachosChip2.setText(preferences.getString("Genin",""))
//        restrictionCheckBox.isChecked = preferences.getBoolean("mission",true)
//        val fetch:Set<String> = preferences.getStringSet("result", emptySet()) as Set<String>
//        output2.text = fetch.toString().replace("[","").replace("]","")
//        destroyedString = preferences.getString("lineResult","").toString()
//
//
//
//        if (output2.text.toString()!=("")){
//            output2.visibility = View.VISIBLE
//            line.visibility = View.VISIBLE
//        }
//        else {
////            output2.visibility = View.GONE
//            output2.text = destroyedString
//            output2.visibility = View.VISIBLE
//            line.visibility = View.VISIBLE
//        }
//
//        //make all to be chipped if a token is exist
//        val nachosChip0Token = nachosChip0.tokenValues.toString()
//        val nachosChip1Token = nachosChip1.tokenValues.toString()
//        val nachosChip2Token = nachosChip2.tokenValues.toString()
//        if (nachosChip0Token!=""){
//            nachosChip0.append("\n")
//        }
//        if (nachosChip1Token!=""){
//            nachosChip1.append("\n")
//        }
//        if (nachosChip2Token!=""){
//            nachosChip2.append("\n")
//        }
//
//
//
//
//        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
//        recyclerView!!.layoutManager = LinearLayoutManager(context)
//
////        restriction1TextView = view.findViewById(R.id.restriction1TextView)
////        restriction2TextView = view.findViewById(R.id.restriction2TextView)
////        restriction1TextView.visibility = View.GONE
////        restriction2TextView.visibility = View.GONE
////        nachosChip1.visibility = View.GONE
////        nachosChip2.visibility = View.GONE
//
//        shuffleButton.setOnClickListener {
//            randomList.clear()
//            randomList1.clear()
//            randomList2.clear()
//            randomList3.clear()
//            output.visibility = View.GONE
//            output2.visibility = View.GONE
//            recyclerView?.visibility = View.VISIBLE
//            shufflePlayer()
//
//
//
//        }
//        resetButton.setOnClickListener {
//            clearFormAndPreferences()
//            Toast.makeText(context,"Clear!",Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun removeSquareBracket(x: String): String {
//        return x.replace("[", "").replace("]", "")
//    }
//
//    private fun removeComma(x: String): String {
//        return x.replace(",", "")
//    }
//
//    private fun clearFormAndPreferences(){
////        nachosChip0.setText("")
////        nachosChip1.setText("")
////        nachosChip2.setText("")
////        checkBox.isChecked = true
//        output2.text = ""
//        recyclerView?.visibility = View.GONE
//        preferences.edit().clear().commit()
//        Log.i("ae","clearFormAndPreferences")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        val editor:SharedPreferences.Editor = preferences.edit()
//        editor.putString("Chunin",nachosChip0.chipValues.toString().replace("[","").replace(",","").replace("]",""))
//        editor.putString("Jounin",nachosChip1.chipValues.toString().replace("[","").replace(",","").replace("]",""))
//        editor.putString("Genin",nachosChip2.chipValues.toString().replace("[","").replace(",","").replace("]",""))
//        editor.putBoolean("mission",restrictionCheckBox.isChecked)
//        editor.putStringSet("result",randomList.toMutableSet())
//        editor.putString("lineResult",output2.text.toString())
//        Log.i("jaishock",output2.toString())
//        editor.commit()
//    }
//
//    private fun shufflePlayer() {
//        //list of chip value
//        val list1 = nachosChip0.chipValues
//        val list2 = nachosChip1.chipValues
//        val list3 = nachosChip2.chipValues
//        numberOfDesiredElement = Integer.parseInt(playerQuantityEditText.text.toString())
//        if (numberOfDesiredElement > 0) {
//            Log.i("jainig", nachosChip0.tokenValues.toString())
//
//            line.visibility = View.VISIBLE
//
//
//            //no duplicate
//
//            if (noDuplicateCheckBox.isChecked){
//                restrictionCheckBox.isChecked = false
//                playerQuantityEditText.setText("2")
//                numberOfDesiredElement = 2
//                while (list1.size > 0){
//                    list1RandomElements = list1.shuffled().take(numberOfDesiredElement)
//                    list1.removeIf { x -> list1RandomElements.contains(x) }
//                    randomListForNonDuplicateElements.addAll(list1RandomElements)
//                    Log.d("noDu",randomListForNonDuplicateElements.toString())
////                    Log.d("noDu", "list1RandomElements: $list1RandomElements")
////                    Log.d("noDu","randomListForNonDuplicateElement: ${randomListForNonDuplicateElements[0]}")
////                    if (list1RandomElements.toString().contains(randomListForNonDuplicateElements[0])) {
////                        Log.d("noDu", "contain!")
////                    }
////                    for (list in randomListForNonDuplicateElements) {
////                        for (element in list) {
////                            Log.d("noDu",(element.toString()))
////                        }
////                    }
//
//                    randomList.add(removeSquareBracket(list1RandomElements.toString()))
//                }
//                if (randomList2.size != 0) {
//                        output.visibility = View.VISIBLE
//                        output.text = removeSquareBracket(randomList2.toString())
//                }
//                Log.d("noDu", "list1RandomElements: $list1RandomElements")
//                Log.d("noDu","randomList: $randomList")
//                Log.d("noDu", "Complete: $randomListForNonDuplicateElements")
//                list1.addAll(randomListForNonDuplicateElements)
//                val numberOfPair = list1.size/numberOfDesiredElement
//                for (round in 0 until numberOfPair){
//
//                }
//                list1RandomElements = list1.shuffled().take(1)
////                randomList1ForNonDuplicateElements.addAll(randomListForNonDuplicateElements)
//                randomList2ForNonDuplicateElements.addAll(randomListForNonDuplicateElements)
////                randomList3ForNonDuplicateElements.addAll(randomListForNonDuplicateElements)
//                Log.d("noDu", "list1RandomElements: $list1RandomElements")
//                var index = randomListForNonDuplicateElements.indexOf(removeSquareBracket(list1RandomElements.toString()))
//
//                Log.d("noDu",
//                    "randomListForNonDuplicateElement: $randomListForNonDuplicateElements"
//                )
//                Log.d("noDu","list1RandomElements: "+removeSquareBracket(list1RandomElements.toString()))
//                Log.d("noDu", "index: $index")
////                var loop = 0
////                while(loop<list1.size){
////                    list1RandomElements = list1.shuffled().take(1)
////                    list1.addAll(randomListForNonDuplicateElements)
////                }
//                for (i in 0 until list1.size) {
//                    println(i)
//                }
//                if (index==0 || index == 1){
//                    Log.d("noDu","0 or 1")
//                    randomListForNonDuplicateElements[0]="0"
//                    randomListForNonDuplicateElements[1]="0"
//                } else
//                if (index==2||index==3){
//                    Log.d("noDu","2 or 3")
//                    randomListForNonDuplicateElements[2]="0"
//                    randomListForNonDuplicateElements[3]="0"
//                } else if (index==4||index==5){
//                    Log.d("noDu","4 or 5")
//                    randomListForNonDuplicateElements[4]="0"
//                    randomListForNonDuplicateElements[5]="0"
//                }
//                randomList1ForNonDuplicateElements.add(removeSquareBracket(list1RandomElements.toString()))
//                Log.d("noDu", "randomList AfterRemove: $randomListForNonDuplicateElements")
//                var returnFlag = false
//                while (!returnFlag) {
//                    list1RandomElements = randomListForNonDuplicateElements.shuffled().take(1)
//                    Log.d("noDu", "randomElement after modified: ${removeSquareBracket(list1RandomElements.toString())}")
//                    if (removeSquareBracket(list1RandomElements.toString()) != "0"){
//                        returnFlag = true
//                        randomList1ForNonDuplicateElements.add(removeSquareBracket(list1RandomElements.toString()))
//                    }
//                }
//                Log.d("noDu","randomList1ForNonDup: $randomList1ForNonDuplicateElements")
//                Log.d("noDu","randomList2ForNonDup: $randomList2ForNonDuplicateElements")
////                randomListForNonDuplicateElements = randomList1ForNonDuplicateElements
////                index = randomListForNonDuplicateElements.indexOf(removeSquareBracket(list1RandomElements.toString()))
//                if (index==0 || index == 1){
//                    Log.d("noDu","0 or 1")
//                    randomListForNonDuplicateElements[0]="0"
//                    randomListForNonDuplicateElements[1]="0"
//                } else
//                    if (index==2||index==3){
//                        Log.d("noDu","2 or 3")
//                        randomListForNonDuplicateElements[2]="0"
//                        randomListForNonDuplicateElements[3]="0"
//                    } else if (index==4||index==5){
//                        Log.d("noDu","4 or 5")
//                        randomListForNonDuplicateElements[4]="0"
//                        randomListForNonDuplicateElements[5]="0"
//                    }
//                randomListForNonDuplicateElements[index]="0"
//                Log.d("noDu","randomListForNonDup: $randomListForNonDuplicateElements")
//                Log.d("noDu","randomList1ForNonDup: $randomList1ForNonDuplicateElements")
////                randomListForNonDuplicateElements.clear()
////                randomList1ForNonDuplicateElements.clear()
////                val containsList: Boolean = randomListForNonDuplicateElements.containsAll(list1RandomElements)
////                if (containsList) {
////                    Log.d("noDu","contains sublist: The list is contained within the ArrayList.")
////                } else {
////                    Log.d("noDu","contains sublist: The list is not contained within the ArrayList.")
////                }
//
////                Log.d("noDu","randomListFirstIndex: "+randomListForNonDuplicateElements[0])
////                Log.d("noDu","list1RandomElementFirstIndex: "+list1RandomElements[0])
////                randomListForNonDuplicateElements.clear()
//
//            } else
//            //2 restrictions
//            //matches restrictions first
//            if (restrictionCheckBox.isChecked) {
//                //while chips are not empty
//                while (list1.size > 0 || list2.size > 0 || list3.size > 0) {
//                    //matches list2 and 3
//                    if ((list2.size != 0 && list3.size != 0)) {
//                        list2RandomElements = list2.shuffled().take(1)
//                        list2.removeIf { x -> list2RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list2RandomElements.toString()))
//                        list3RandomElements = list3.shuffled().take(1)
//                        list3.removeIf { x -> list3RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list3RandomElements.toString()))
//                        randomList.add(removeSquareBracket(randomList2.toString()))
//                        randomList2.clear()
//
//
//                        //list 1 and list 2 are not empty, list 3 is
//                    } else if (list3.size == 0 && list2.size != 0 && list1.size != 0) {
//                        list2RandomElements = list2.shuffled().take(1)
//                        list2.removeIf { x -> list2RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list2RandomElements.toString()))
//                        list1RandomElements = list1.shuffled().take(numberOfDesiredElement - 1)
//                        list1.removeIf { x -> list1RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list1RandomElements.toString()))
//                        randomList.add(removeSquareBracket(randomList2.toString()))
//                        randomList2.clear()
//
//                        //list 1 and 3 are not empty, list 2 is
//                    } else if (list2.size == 0 && list1.size != 0 && list3.size != 0) {
//                        list1RandomElements = list1.shuffled().take(numberOfDesiredElement - 1)
//                        list1.removeIf { x -> list1RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list1RandomElements.toString()))
//                        list3RandomElements = list3.shuffled().take(1)
//                        list3.removeIf { x -> list3RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list3RandomElements.toString()))
//                        randomList.add(removeSquareBracket(randomList2.toString()))
//                        randomList2.clear()
//
//                        //only non-restriction is left
//                    } else if (list1.size > 0) {
//                        list1RandomElements = list1.shuffled().take(numberOfDesiredElement)
//                        list1.removeIf { x -> list1RandomElements.contains(x) }
//                        randomList.add(removeSquareBracket(list1RandomElements.toString()))
//                    }
//                }//after while loop (chips are empty)
//                if (randomList2.size != 0) {
//                    output.visibility = View.VISIBLE
//                    output.text = removeSquareBracket(randomList2.toString())
//                }
//
//                // with all randomly
//            } else {
//
////            for debug
////            if (list1.size + list2.size <= list3.size) {
////                list2RandomElements = list2.shuffled().take(1)
////                list2.removeIf { x -> list2RandomElements.contains(x) }
////                randomList2.add(removeSquareBracket(list2RandomElements.toString()))
////                list1RandomElements = list1.shuffled().take(1)
////                list1.removeIf { x -> list1RandomElements.contains(x) }
////                randomList2.add(removeSquareBracket(list1RandomElements.toString()))
////                randomList1 = ArrayList(randomList2.shuffled().take(1))
////                randomList2.removeIf { x -> randomList1.contains(x) }
////                randomList3.add(removeSquareBracket(randomList1.toString()))
////                randomList1.clear()
////                //return shuffled value to listX
////                when {
////                    nachosChip0.chipValues.containsAll(randomList2) -> {
////                        list1.addAll(randomList2)
////                    }
////                    nachosChip1.chipValues.containsAll(randomList2) -> {
////                        list2.addAll(randomList2)
////                    }
////                    nachosChip2.chipValues.containsAll(randomList2) -> {
////                        list3.addAll(randomList2)
////                    }
////                }
////                list3RandomElements = list3.shuffled().take(1)
////                list3.removeIf { x -> list3RandomElements.contains(x) }
////                randomList3.add(removeSquareBracket(list3RandomElements.toString()))
////                randomList.add(removeSquareBracket(randomList3.toString()))
////                randomList2.clear()
////            }
//
//
////                output.visibility = View.VISIBLE
////                output2.visibility = View.VISIBLE
////                output3.visibility = View.VISIBLE
////                output4.visibility = View.VISIBLE
////                output5.visibility = View.VISIBLE
////                output.text = "list1: "+list1
////                output2.text = "list2: "+list2
////                output3.text = "list3: $list3"
////                output4.text = "randomList1: $randomList1"
////                output5.text = "randomList2: $randomList2"
////            }
//
////             shuffle with 2 restriction
//                while (list1.size > 0 || list2.size > 0 || list3.size > 0) {
//
//                    //all are equal or none are 0
////                if list 1 is empty
//                    if (list1.isEmpty() && list2.isNotEmpty() && list3.isNotEmpty() && ((list2.size - list3.size < numberOfDesiredElement) || (list3.size - list2.size < numberOfDesiredElement))) {
//                        list2RandomElements = list2.shuffled().take(1)
//                        list2.removeIf { x -> list2RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list2RandomElements.toString()))
//                        list3RandomElements = list3.shuffled().take(1)
//                        list3.removeIf { x -> list3RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list3RandomElements.toString()))
//                        randomList.add(removeSquareBracket(randomList2.toString()))
//                        randomList2.clear()
//                    }
////                if list2 is empty
//                    else if (list2.isEmpty() && list1.isNotEmpty() && list3.isNotEmpty()) {
//                        list1RandomElements = list1.shuffled().take(1)
//                        list1.removeIf { x -> list1RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list1RandomElements.toString()))
//                        list3RandomElements = list3.shuffled().take(1)
//                        list3.removeIf { x -> list3RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list3RandomElements.toString()))
//                        randomList.add(removeSquareBracket(randomList2.toString()))
//                        randomList2.clear()
//
//                        //if list3 is empty
//                    } else if (list3.isEmpty() && list1.isNotEmpty() && list2.isNotEmpty()) {
//                        list1RandomElements = list1.shuffled().take(1)
//                        list1.removeIf { x -> list1RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list1RandomElements.toString()))
//                        list2RandomElements = list2.shuffled().take(1)
//                        list2.removeIf { x -> list2RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list2RandomElements.toString()))
//                        randomList.add(removeSquareBracket(randomList2.toString()))
//                        randomList2.clear()
//
//                        //if list2 and 3 are empty
//                    } else if (list2.isEmpty() && list3.isEmpty()) {
//                        list1RandomElements = list1.shuffled().take(numberOfDesiredElement)
//                        list1.removeIf { x -> list1RandomElements.contains(x) }
//                        randomList.add(removeSquareBracket(list1RandomElements.toString()))
//
//                        //1 and 3 are combined and equal 2 or 1 and 2 are combined and equal 3
//                    } else if ((list1.size + list3.size <= list2.size)) {
//                        list3RandomElements = list3.shuffled().take(1)
//                        list3.removeIf { x -> list3RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list3RandomElements.toString()))
//                        list1RandomElements = list1.shuffled().take(1)
//                        list1.removeIf { x -> list1RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list1RandomElements.toString()))
//                        randomList1 = ArrayList(randomList2.shuffled().take(1))
//                        randomList2.removeIf { x -> randomList1.contains(x) }
//                        randomList3.add(removeSquareBracket(randomList1.toString()))
//                        randomList1.clear()
//                        //return shuffled value to listX
//                        when {
//                            nachosChip0.chipValues.containsAll(randomList2) -> {
//                                list1.addAll(randomList2)
//                            }
//                            nachosChip1.chipValues.containsAll(randomList2) -> {
//                                list2.addAll(randomList2)
//                            }
//                            nachosChip2.chipValues.containsAll(randomList2) -> {
//                                list3.addAll(randomList2)
//                            }
//                        }
//
//                        list2RandomElements = list2.shuffled().take(1)
//                        list2.removeIf { x -> list2RandomElements.contains(x) }
//                        randomList3.add(removeSquareBracket(list2RandomElements.toString()))
//                        randomList.add(removeSquareBracket(randomList3.toString()))
//                        randomList2.clear()
//                        randomList3.clear()
//
//                    } else if (list1.size + list2.size <= list3.size) {
//                        list2RandomElements = list2.shuffled().take(1)
//                        list2.removeIf { x -> list2RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list2RandomElements.toString()))
//                        list1RandomElements = list1.shuffled().take(1)
//                        list1.removeIf { x -> list1RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list1RandomElements.toString()))
//                        randomList1 = ArrayList(randomList2.shuffled().take(1))
//                        randomList2.removeIf { x -> randomList1.contains(x) }
//                        randomList3.add(removeSquareBracket(randomList1.toString()))
//                        randomList1.clear()
//                        //return shuffled value to listX
//                        when {
//                            nachosChip0.chipValues.containsAll(randomList2) -> {
//                                list1.addAll(randomList2)
//                            }
//                            nachosChip1.chipValues.containsAll(randomList2) -> {
//                                list2.addAll(randomList2)
//                            }
//                            nachosChip2.chipValues.containsAll(randomList2) -> {
//                                list3.addAll(randomList2)
//                            }
//                        }
//                        list3RandomElements = list3.shuffled().take(1)
//                        list3.removeIf { x -> list3RandomElements.contains(x) }
//                        randomList3.add(removeSquareBracket(list3RandomElements.toString()))
//                        randomList.add(removeSquareBracket(randomList3.toString()))
//                        randomList2.clear()
//                        randomList3.clear()
//                    } else if (list2.size < numberOfDesiredElement && list3.size >= numberOfDesiredElement && list1.size >= numberOfDesiredElement) {
//                        list1RandomElements = list1.shuffled().take(1)
//                        list1.removeIf { x -> list1RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list1RandomElements.toString()))
//                        list3RandomElements = list3.shuffled().take(1)
//                        list3.removeIf { x -> list3RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list3RandomElements.toString()))
//                        randomList.add(removeSquareBracket(randomList2.toString()))
//                        randomList2.clear()
//                    }
//                    //prevent comma bug
//                    else if ((((list2.size - list3.size) < numberOfDesiredElement) && (list2.size - list3.size) > 0)) {
//                        list3RandomElements = list3.shuffled().take(1)
//                        list3.removeIf { x -> list3RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list3RandomElements.toString()))
//                        list1RandomElements = list1.shuffled().take(1)
//                        list1.removeIf { x -> list1RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list1RandomElements.toString()))
//                        randomList1 = ArrayList(randomList2.shuffled().take(1))
//                        randomList2.removeIf { x -> randomList1.contains(x) }
//                        randomList3.add(removeSquareBracket(randomList1.toString()))
//                        randomList1.clear()
//                        //return shuffled value to listX
//                        when {
//                            nachosChip0.chipValues.containsAll(randomList2) -> {
//                                list1.addAll(randomList2)
//                            }
//                            nachosChip1.chipValues.containsAll(randomList2) -> {
//                                list2.addAll(randomList2)
//                            }
//                            nachosChip2.chipValues.containsAll(randomList2) -> {
//                                list3.addAll(randomList2)
//                            }
//                        }
//
//                        list2RandomElements = list2.shuffled().take(1)
//                        list2.removeIf { x -> list2RandomElements.contains(x) }
//                        randomList3.add(removeSquareBracket(list2RandomElements.toString()))
//                        randomList.add(removeSquareBracket(randomList3.toString()))
//                        randomList2.clear()
//                        randomList3.clear()
//                    }
//                    //prevent comma bug
//                    else if ((((list3.size - list2.size) < numberOfDesiredElement) && (list3.size - list2.size) > 0)) {
//                        list2RandomElements = list2.shuffled().take(1)
//                        list2.removeIf { x -> list2RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list2RandomElements.toString()))
//                        list1RandomElements = list1.shuffled().take(1)
//                        list1.removeIf { x -> list1RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list1RandomElements.toString()))
//                        randomList1 = ArrayList(randomList2.shuffled().take(1))
//                        randomList2.removeIf { x -> randomList1.contains(x) }
//                        randomList3.add(removeSquareBracket(randomList1.toString()))
//                        randomList1.clear()
//                        //return shuffled value to listX
//                        when {
//                            nachosChip0.chipValues.containsAll(randomList2) -> {
//                                list1.addAll(randomList2)
//                            }
//                            nachosChip1.chipValues.containsAll(randomList2) -> {
//                                list2.addAll(randomList2)
//                            }
//                            nachosChip2.chipValues.containsAll(randomList2) -> {
//                                list3.addAll(randomList2)
//                            }
//                        }
//                        list3RandomElements = list3.shuffled().take(1)
//                        list3.removeIf { x -> list3RandomElements.contains(x) }
//                        randomList3.add(removeSquareBracket(list3RandomElements.toString()))
//                        randomList.add(removeSquareBracket(randomList3.toString()))
//                        randomList2.clear()
//                        randomList3.clear()
//
//
//                    }
//                    //prevent comma bug
//                    else if ((list1.size == list2.size) && list1.size == list3.size) {
//                        list2RandomElements = list2.shuffled().take(1)
//                        list2.removeIf { x -> list2RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list2RandomElements.toString()))
//                        list3RandomElements = list3.shuffled().take(1)
//                        list3.removeIf { x -> list3RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list3RandomElements.toString()))
//                        randomList.add(removeSquareBracket(randomList2.shuffled().toString()))
//                        randomList2.clear()
//                    } else {
//                        list1RandomElements = list1.shuffled().take(1)
//                        list1.removeIf { x -> list1RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list1RandomElements.toString()))
//                        list2RandomElements = list2.shuffled().take(1)
//                        list2.removeIf { x -> list2RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list2RandomElements.toString()))
//                        list3RandomElements = list3.shuffled().take(1)
//                        list3.removeIf { x -> list3RandomElements.contains(x) }
//                        randomList2.add(removeSquareBracket(list3RandomElements.toString()))
//                        randomList1 =
//                            randomList2.shuffled().take(numberOfDesiredElement) as ArrayList<String>
//                        randomList2.removeIf { x -> randomList1.contains(x) }
//                        randomList.add(removeSquareBracket(randomList1.toString()))
//                        randomList1.clear()
//
//                        //return shuffled value to listX
//                        when {
//                            nachosChip0.chipValues.containsAll(randomList2) -> {
//                                list1.addAll(randomList2)
//                            }
//                            nachosChip1.chipValues.containsAll(randomList2) -> {
//                                list2.addAll(randomList2)
//                            }
//                            nachosChip2.chipValues.containsAll(randomList2) -> {
//                                list3.addAll(randomList2)
//                            }
//                        }
//
//
//                        //clear randomList2 after added
//                        randomList2.clear()
//
//
//                    }
//
//
//                }
//
//            }
//
//
//            //add item to adapter
//            val itemAdapter = MyAdapter(randomList, requireActivity())
//            recyclerView!!.adapter = itemAdapter
//            Toast.makeText(context, "Shuffled!", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(context, "Please enter player quantity per team", Toast.LENGTH_SHORT)
//                .show()
//        }
//    }
//
//}
//
//
//
//
//
//
//
//
