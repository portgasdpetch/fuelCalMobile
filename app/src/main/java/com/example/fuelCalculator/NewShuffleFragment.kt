package com.example.fuelCalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class NewShuffleFragment : Fragment() {
    private lateinit var drawer: DrawerLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        drawer = requireActivity().findViewById(R.id.drawer_layout);
        val v: View = inflater.inflate(R.layout.fragment_shuffle, container, false)

        setHasOptionsMenu(true)
        return v


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chipGroup = requireActivity().findViewById<ChipGroup>(R.id.chipGroup)
        val chip = Chip(requireContext()).apply {
            text = "Example Chip"
            isCloseIconVisible = true
            setOnCloseIconClickListener {
                chipGroup.removeView(this)
            }
        }
        chipGroup.addView(chip)
    }




}