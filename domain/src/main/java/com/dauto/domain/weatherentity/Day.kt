package com.dauto.domain.weatherentity

data class Day(

    val date: String,

    val avgTemperature: String,

    val wind: String,

    val humidity: String,

    val chanceRain: String,

    val chanceSnow: String,

    val condition: Condition,

    val astro: Astro,

    )
