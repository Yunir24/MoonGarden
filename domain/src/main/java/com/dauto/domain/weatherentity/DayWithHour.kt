package com.dauto.domain.weatherentity

data class DayWithHour(

    val date: String,
    val day: Day,
    val astro: Astro,
    val hour: List<Hour>
)
