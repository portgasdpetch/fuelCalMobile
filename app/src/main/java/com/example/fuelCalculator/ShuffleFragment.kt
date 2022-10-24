package com.example.fuelCalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import com.hootsuite.nachos.NachoTextView
import kotlinx.android.synthetic.main.fragment_shuffle.*

class ShuffleFragment : Fragment() {
    private lateinit var  nonRestriction:NachoTextView
    private lateinit var restriction1:TextInputEditText
    private lateinit var restriction2:TextInputEditText
    private lateinit var chip1:Chip; private lateinit var chip2:Chip; private lateinit var chip3:Chip

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
        nonRestriction = view.findViewById(R.id.restriction0EditText)
        restriction1 = view.findViewById(R.id.restriction1EditText)
        restriction2 = view.findViewById(R.id.restriction2EditText)
        val chipGroup:ChipGroup = view.findViewById(R.id.chip_group)
        val shuffleButton: Button = view.findViewById(R.id.shuffle_button)

    }
}