package com.dauto.moongarden.fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.dauto.moongarden.MainViewModel
import com.dauto.moongarden.databinding.FragmentMainBinding
import com.dauto.moongarden.location.DialogManager
import com.dauto.moongarden.location.LocationState
import com.dauto.moongarden.location.LocationStateListener
import com.dauto.moongarden.location.isPermissionGranted
import com.dauto.moongarden.state.CurrentWeatherState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource


class MainFragment : Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()

    private lateinit var currentLocation: String
    private lateinit var sharedPref: SharedPreferences
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var launcher: ActivityResultLauncher<String>
    private lateinit var locationStateListener: LocationStateListener

    private var currentLocationExist = false
    private var isChangeLocation = false

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("MainBinding is not exist")

    override fun onAttach(context: Context) {
        getSettingsInSharedPreference()
        if (context is LocationStateListener) {
            locationStateListener = context
        } else {
            throw RuntimeException("Activity must implement LocationStateListener")
        }
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        permissionListener()
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        if (isChangeLocation) {
            getLocation()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        if (!currentLocationExist) {
            checkPermission()
        }
        setClickListener()
        observeViewModel()
        sharedViewModel.getMoonDay()
    }

    private fun setClickListener() {
        binding.locationTV.setOnClickListener {
            getLocation()
        }
        binding.buttonGetWeather.setOnClickListener {
            sharedViewModel.updateCurrentWeather(currentLocation)
        }
    }

    private fun observeViewModel() {
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                sharedViewModel.currentWeather.observe(viewLifecycleOwner)
                    {
                        when (it) {
                            is CurrentWeatherState.Loading -> binding.progressBar.visibility =
                                View.VISIBLE
                            is CurrentWeatherState.Error -> {
                                binding.progressBar.visibility = View.INVISIBLE
                            }
                            is CurrentWeatherState.Result -> {
                                with(binding) {
                                    progressBar.visibility = View.INVISIBLE
                                    temperatureTV.text = it.currentWeather.temperature
                                    lastUpdate.text = it.currentWeather.lastUpdate
                                    conditionTextTV.text = it.currentWeather.condition.text
                                    locationTV.text = it.currentWeather.location
                                    windTV.text = it.currentWeather.wind
                                    humidityTV.text = it.currentWeather.humidity
                                    chanceRainTV.text = it.currentWeather.cloud
                                    feelsTemperatureTV.text = it.currentWeather.feelsLike
                                    Glide.with(requireContext())
                                        .load("https:${it.currentWeather.condition.icon}")
                                        .into(conditionIconIV)
                                }
                            }
                        }
//                    }
            }
//        }
        sharedViewModel.moonDay.observe(viewLifecycleOwner) {
            binding.moonPhaseInfoTV.text = it.moonInfo
            binding.moonDayInfoTV.text = it.description
        }
    }

    private fun getSettingsInSharedPreference() {
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        currentLocationExist = sharedPref.getBoolean(LOCATION_SAVE_KEY, false)
        currentLocation = sharedPref.getString(LOCATION_VALUE_KEY, LOCATION_DEFAULT_VALUE)
            ?: LOCATION_DEFAULT_VALUE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun isGPSEnabled(): Boolean {
        val locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun checkLocation() {
        if (isGPSEnabled()) {
            getLocation()
        } else {
            DialogManager.locationSettingsDialog(requireContext(),
                object : DialogManager.Listener {
                    override fun onClickPositive() {
                        isChangeLocation = true
                        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    }

                    override fun onClickNegative() {
                        isChangeLocation = false
                        locationStateListener.showState(LocationState.DISABLED_GPS)
                    }
                })
        }
    }

    private fun getLocation() {
        if (!isGPSEnabled()) {
            checkLocation()
            return
        }
        val priority = Priority.PRIORITY_HIGH_ACCURACY
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            isChangeLocation = true
            return
        }
        fusedLocationClient
            .getCurrentLocation(priority, CancellationTokenSource().token)
            .addOnSuccessListener(requireContext() as Activity) { location: Location? ->
                //работает только после включения геолокации
                isChangeLocation = false
                if (location == null) {
                    locationStateListener.showState(LocationState.LOCATION_NOT_FOUND)
                } else {
                    locationStateListener.showState(LocationState.LOCATION_FOUND)
                    currentLocation = "${location.latitude}, ${location.longitude}"
                    sharedPref.edit()
                        .putString(
                            LOCATION_VALUE_KEY,
                            currentLocation
                        )
                        .putBoolean(LOCATION_SAVE_KEY, true)
                        .apply()
                    sharedViewModel.updateCurrentWeather(currentLocation)
                }
            }

    }

    private fun permissionListener() {
        launcher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) {
                if (!it) {
                    locationStateListener.showState(LocationState.DENY_LOCATION)
                    isChangeLocation = false
                    currentLocationExist = true
                    sharedPref.edit()
                        .putBoolean(LOCATION_SAVE_KEY, true)
                        .apply()
                }

            }

    }

    private fun checkPermission() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            currentLocationExist = true
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            getLocation()
        }
    }





    companion object {


        private const val LOCATION_SAVE_KEY = "comdautoLocationExist"
        const val LOCATION_VALUE_KEY = "comdautomoongardenLocationValue"
        const val LOCATION_DEFAULT_VALUE = "53.334301, 58.518090"
    }


}