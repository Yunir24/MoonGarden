package com.dauto.moongarden

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dauto.moongarden.databinding.ActivityMainBinding
import com.dauto.moongarden.location.LocationState
import com.dauto.moongarden.location.LocationStateListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(), LocationStateListener {
    private val  viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        analytics = Firebase.analytics
        navController =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                    as NavHostFragment).navController
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        navView.setupWithNavController(navController)
        if(!isInternetAvailable(this)){
            showState(LocationState.NO_INTERNET)
        }

    }

    private fun isInternetAvailable(context: Context): Boolean=
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }

    override fun onStop() {
        super.onStop()
        viewModel.viewModelScope
    }

    private fun showSnackBar(stringId: Int) {
        Snackbar.make(binding.layoutForSnackBar, stringId, Snackbar.LENGTH_SHORT).show()
    }

    override fun showState(locationState: LocationState) {
        when(locationState){
            LocationState.LOCATION_NOT_FOUND -> showSnackBar(R.string.not_found_location)
            LocationState.LOCATION_FOUND -> showSnackBar(R.string.found_location)
            LocationState.DENY_LOCATION -> showSnackBar(R.string.deny_location)
            LocationState.DISABLED_GPS -> showSnackBar(R.string.turn_on_gps)
            LocationState.UPDATE_MESSAGE -> showSnackBar(R.string.update_message)
            LocationState.NO_INTERNET -> showSnackBar(R.string.no_internet_attention)
        }
    }



}