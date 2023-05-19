package com.dauto.moongarden.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dauto.moongarden.MainViewModel
import com.dauto.moongarden.adapters.WeatherVerticalRCAdapter
import com.dauto.moongarden.databinding.FragmentWeatherDetailBinding
import com.dauto.moongarden.location.LocationState
import com.dauto.moongarden.location.LocationStateListener


class WeatherDetailFragment : Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var locationStateListener: LocationStateListener
    private var currentLocation: String = MainFragment.LOCATION_DEFAULT_VALUE
    private lateinit var sharedPref: SharedPreferences
    private var _binding: FragmentWeatherDetailBinding? = null
    private val binding: FragmentWeatherDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentWeatherDetailBinding is not exist")

    override fun onAttach(context: Context) {
        getLocationInSharedPreference()
        if (context is LocationStateListener) {
            locationStateListener = context
        } else {
            throw RuntimeException("Activity must implement LocationStateListener")
        }
        super.onAttach(context)
    }

    private fun getLocationInSharedPreference() {
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        currentLocation = sharedPref.getString(
            MainFragment.LOCATION_VALUE_KEY,
            MainFragment.LOCATION_DEFAULT_VALUE
        )
            ?: MainFragment.LOCATION_DEFAULT_VALUE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verticalAdapter = WeatherVerticalRCAdapter()
        binding.vertivalRCView.adapter=verticalAdapter
        sharedViewModel.forecastWeatherList.observe(viewLifecycleOwner){
            verticalAdapter.submitList(it)
            binding.swipeLayout.isRefreshing=false
        }
        binding.swipeLayout.setOnRefreshListener {
            locationStateListener.showState(LocationState.UPDATE_MESSAGE)
            sharedViewModel.updateCurrentWeather(currentLocation)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}