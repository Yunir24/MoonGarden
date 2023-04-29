package com.dauto.moongarden.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dauto.moongarden.MainViewModel
import com.dauto.moongarden.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("MainBinding is not exist")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("viewCheck", "mainfrag create")
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.getWeather()
        sharedViewModel.weather.observe(viewLifecycleOwner){
            Log.d("viewCheck",it.location.name)
        }
//        sharedViewModel.moonDay.observe(viewLifecycleOwner){
//            binding.textViewDayInfo.text=it.description
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("viewCheck", "mainfrag destroy")
        _binding = null
    }


}