package com.example.fuelCalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChipAdapter(private val chips: MutableList<String>) :
    RecyclerView.Adapter<ChipAdapter.ChipViewHolder>() {

    class ChipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        val chipView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chip, parent, false)
        return ChipViewHolder(chipView)
    }

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        val chipText = chips[position]
        holder.itemView.findViewById<TextView>(R.id.chipTextView).text = chipText
    }

    override fun getItemCount(): Int {
        return chips.size
    }
}
