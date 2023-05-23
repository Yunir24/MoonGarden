package com.dauto.moongarden.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dauto.moongarden.adapters.DaysAdapter
import com.dauto.moongarden.MainViewModel
import com.dauto.moongarden.databinding.FragmentDayBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase


class DayFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private val daysAdapter = DaysAdapter()

    private lateinit var analytics: FirebaseAnalytics
    private var _binding: FragmentDayBinding? = null
    private val binding: FragmentDayBinding
        get() = _binding ?: throw RuntimeException("FragmentDayBinding is not exist")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        analytics = Firebase.analytics
        _binding = FragmentDayBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, "DayFragment")
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "DayFragment")
        }
        binding.dayListRcView.adapter=daysAdapter
        sharedViewModel.moonMonth.observe(viewLifecycleOwner){
            daysAdapter.submitList(it.daysList)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    companion object {

        fun newInstance() =
            DayFragment().apply {

            }
    }
}