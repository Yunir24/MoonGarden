package com.dauto.moongarden.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dauto.moongarden.MainViewModel
import com.dauto.moongarden.databinding.FragmentMonthBinding


class MonthFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentMonthBinding? = null
    private val binding: FragmentMonthBinding
        get() = _binding ?: throw RuntimeException("FragmentMonthBinding is not exist")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("viewCheck", "month create")
        _binding = FragmentMonthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        sharedViewModel.moonMonth.observe(viewLifecycleOwner){
//            binding.textViewForMonth.text = it.month.description
//        }
    }

    companion object {

        fun newInstance() =
            MonthFragment().apply {

            }
    }
    override fun onDestroy() {
        Log.d("viewCheck", "month destroy")
        super.onDestroy()
        _binding = null
    }
}