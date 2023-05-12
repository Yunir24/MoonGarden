package com.dauto.moongarden.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dauto.moongarden.DaysAdapter
import com.dauto.moongarden.MainViewModel
import com.dauto.moongarden.databinding.FragmentDayBinding


class DayFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private val daysAdapter = DaysAdapter()

    private var _binding: FragmentDayBinding? = null
    private val binding: FragmentDayBinding
        get() = _binding ?: throw RuntimeException("FragmentDayBinding is not exist")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("viewCheck", "day create")
        _binding = FragmentDayBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dayListRcView.adapter=daysAdapter
        sharedViewModel.moonMonth.observe(viewLifecycleOwner){
            daysAdapter.moonDayList = it.daysList
        }
    }

    override fun onDestroy() {
        Log.d("viewCheck", "day destroy")
        super.onDestroy()
        _binding = null
    }
    companion object {

        fun newInstance() =
            DayFragment().apply {

            }
    }
}