package com.example.fuelCalculator

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ChipSpaceTextWatcher(private val chipGroup: ChipGroup, private val editText: EditText) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // This method is called before the text is changed.
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // This method is called when the text is being changed.
    }

    override fun afterTextChanged(s: Editable?) {
        // This method is called after the text has been changed.
//      val text = s.toString()
//        if (text.endsWith(" ")) { // Check if spacebar was pressed
//            val chipText = text.trim()
//            if (chipText.isNotEmpty()) {
//                createChip(chipText)
//                // Move cursor to the end of the EditText
//                editText.setSelection(editText.length())
//            }
//        }
    }
    private fun createChip(chipText: String) {
        val chip = Chip(chipGroup.context)
        chip.text = chipText
        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener {
            chipGroup.removeView(chip)
        }
        chipGroup.addView(chip)
    }

    fun handleSpacePressed(text: CharSequence?) {
        text?.let {
            val input = it.toString()
            if (input.endsWith(" ")) {
                val chipText = input.trim()
                if (chipText.isNotEmpty()) {
                    val chip = Chip(chipGroup.context)
                    chip.text = chipText
                    chip.isCloseIconVisible = true
                    chip.setOnCloseIconClickListener {
                        chipGroup.removeView(chip)
                    }
                    chipGroup.addView(chip)
                    // Move cursor to the end of EditText
                    editText.setSelection(editText.text.length)
                }
            }
        }
    }
}
