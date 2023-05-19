package com.dauto.data.network.models

import com.google.gson.annotations.SerializedName

data class DayWeatherDto(
    @SerializedName("maxtemp_c")
    val maxtemp: Float,
    @SerializedName("mintemp_c")
    val mintemp: Float,
    @SerializedName("maxwind_kph")
    val wind: Float,
    @SerializedName("avghumidity")
    val humidity: Float,
    @SerializedName("daily_chance_of_rain")
    val chanceRain: Int,
    @SerializedName("daily_chance_of_snow")
    val chanceSnow: Int,
    @SerializedName("condition")
    val condition: ConditionsDto
)
