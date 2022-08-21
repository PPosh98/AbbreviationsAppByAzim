package com.example.abbreviationsappbyazim.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abbreviationsappbyazim.R
import com.example.abbreviationsappbyazim.databinding.CardItemBinding
import com.example.abbreviationsappbyazim.models.Abbreviations

class ViewPagerAdapter(private val abbreviationsList: Abbreviations) : RecyclerView.Adapter<ViewPagerAdapter.AbbreviationsViewHolder>() {

    class AbbreviationsViewHolder(abbrevItem: View) : RecyclerView.ViewHolder(abbrevItem) {
        val binding = CardItemBinding.bind(abbrevItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AbbreviationsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
    )

    override fun onBindViewHolder(holder: AbbreviationsViewHolder, position: Int) {
        holder.binding.acronym.text = abbreviationsList[0].lfs[position].lf
        holder.binding.frequency.text = abbreviationsList[0].lfs[position].freq.toString()
        holder.binding.since.text = abbreviationsList[0].lfs[position].since.toString()
        holder.binding.variances.text = abbreviationsList[0].lfs[position].vars.size.toString()
    }

    override fun getItemCount() = abbreviationsList[0].lfs.size

}