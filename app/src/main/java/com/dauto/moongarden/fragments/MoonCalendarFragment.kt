package com.dauto.moongarden.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dauto.moongarden.MainViewModel
import com.dauto.moongarden.adapters.ViewPagerAdapter
import com.dauto.moongarden.databinding.FragmentMoonCalendarBinding
import com.google.android.material.tabs.TabLayoutMediator


class MoonCalendarFragment : Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()

    val listFragments = listOf(
        MonthFragment.newInstance(),
        DayFragment.newInstance()
    )
    val listFragmentName = listOf(
        " Месяц "," Дни "
    )

    private var _binding: FragmentMoonCalendarBinding? = null
    private val binding: FragmentMoonCalendarBinding
        get() = _binding ?: throw RuntimeException("FragmentMoonCalendarBinding is not exist")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoonCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.getMonthInfo()
        initViewPager()
    }

    private fun initViewPager()= with(binding){
        val adapter =ViewPagerAdapter(this@MoonCalendarFragment,listFragments)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout,viewPager){
            t,p-> t.text = listFragmentName[p]
        }.attach()
    }

    override fun onDestroy() {
        Log.d("viewCheck", "calendar death")
        super.onDestroy()
        _binding = null
    }
}