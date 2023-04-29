package com.dauto.moongarden

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dauto.domain.moonentity.MoonDay

class DaysAdapter() : RecyclerView.Adapter<DaysViewHolder>() {


    private val moonDayList = mutableListOf<MoonDay>()

    init {
        for (i in 1..10){
            moonDayList.add(
                MoonDay("0$i-05-23",
            "Луна в затмении",
                    "В феврале мы всерьез задумываемся о выращивании рассады. Но прежде чем бросить в землю первые семена, реально оцениваем нашу возможность создать для рассады условия близкие к оптимальным. Выбирая сроки посева, не забываем неоспоримую истину: ранняя рассада развивается медленнее рассады, посеянной в более поздние сроки"
                )
            )
        }
    }

    var _moonDayList: List<MoonDay> = listOf()
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