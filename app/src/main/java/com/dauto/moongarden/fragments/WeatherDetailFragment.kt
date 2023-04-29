package com.dauto.moongarden.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dauto.moongarden.databinding.FragmentMoonCalendarBinding
import com.dauto.moongarden.databinding.FragmentWeatherDetailBinding


class WeatherDetailFragment : Fragment() {


    private var _binding: FragmentWeatherDetailBinding? = null
    private val binding: FragmentWeatherDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentWeatherDetailBinding is not exist")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("viewCheck", "weather create")
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroy() {
        Log.d("viewCheck", "wethaer death")
        super.onDestroy()
        _binding = null
    }
}