package com.dauto.moongarden.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dauto.domain.moonentity.MoonDay
import com.dauto.moongarden.databinding.DaysItemBinding

class DaysAdapter() : ListAdapter<MoonDay, DaysAdapter.DayViewHolder>(DaysDiffCallback) {

    private object DaysDiffCallback : DiffUtil.ItemCallback<MoonDay>() {
        override fun areItemsTheSame(oldItem: MoonDay, newItem: MoonDay): Boolean {
            return oldItem.day == newItem.day
        }

        override fun areContentsTheSame(oldItem: MoonDay, newItem: MoonDay): Boolean {
            return oldItem == newItem
        }

    }

    class DayViewHolder(val binding: DaysItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
       val binding = DaysItemBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,
           false
       )
        return DayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = getItem(position)
        with(holder) {
            with(binding) {
                dateTV.text = day.day
                moonPhaseTV.text = day.moonPhase
                dayInfoTV.text = day.description
                moonInfoTV.text = day.moonInfo
            }
        }
    }




}