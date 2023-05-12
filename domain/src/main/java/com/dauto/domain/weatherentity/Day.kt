package com.dauto.domain.weatherentity

data class Day(

    val date: String,

    val maxTemp: String,

    val minTemp: String,

    val chanceRain: Int,

    val condition: Condition,

    val astro: Astro,

)
