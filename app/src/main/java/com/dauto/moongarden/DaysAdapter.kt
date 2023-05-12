package com.dauto.moongarden

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dauto.domain.moonentity.MoonDay

class DaysAdapter() : RecyclerView.Adapter<DaysViewHolder>() {





    var moonDayList: List<MoonDay> = listOf()
        set(value){
            field=value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.days_item,
                parent,
                false)
        return DaysViewHolder(view)
    }

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        val day = moonDayList[position]
        with(holder){
            tvDay.text = day.day
            tvPhase.text = day.moonPhase
            tvDayInfo.text = day.description
        }
    }

    override fun getItemCount(): Int {
        return moonDayList.size
    }
}