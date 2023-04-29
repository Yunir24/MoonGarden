package com.dauto.moongarden

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DaysViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val tvDay = view.findViewById<TextView>(R.id.dateTV)
    val tvPhase = view.findViewById<TextView>(R.id.moonPhaseTV)
    val tvDayInfo = view.findViewById<TextView>(R.id.dayInfoTV)

}