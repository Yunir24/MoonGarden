package com.dauto.moongarden

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dauto.moongarden.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val  viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        viewModel.getMoonDay("02-01-23")
        navController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
//            Navigation.findNavController(this,R.id.fragmentContainerView)
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        navView.setupWithNavController(navController)


//        with(viewModel){
//            getMoonDay("01-01-23")
//            getMonthInfo("february")
//            setDay(MoonDay(
//                "20-04-23",
//                "moon into my ass",
//                "i'm sid down and my ass is burn so mach"
//            ))
//        }
//        getDayInfo()
//        setMonthInfo()

    }

    override fun onStop() {
        super.onStop()
        viewModel.viewModelScope
    }



}