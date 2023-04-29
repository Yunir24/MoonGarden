package com.dauto.moongarden.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dauto.moongarden.fragments.MoonCalendarFragment

class ViewPagerAdapter(
    calendarFragment: MoonCalendarFragment,
    private val list: List<Fragment>
) : FragmentStateAdapter(calendarFragment) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}