package com.dauto.data.network.models

import com.google.gson.annotations.SerializedName

data class DayWeatherDto(
    @SerializedName("maxtemp_c")
    val maxtemp: String,
    @SerializedName("mintemp_c")
    val mintemp: String,
    @SerializedName("daily_chance_of_rain")
    val chanceRain: Int,
    @SerializedName("condition")
    val condition: ConditionsDto
)
