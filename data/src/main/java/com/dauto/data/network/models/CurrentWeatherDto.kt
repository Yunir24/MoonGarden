package com.dauto.data.network.models

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDto(

    @SerializedName("last_updated")
    val lastUpdate: String,

    @SerializedName("temp_c")
    val temperature: Float,

    @SerializedName("condition")
    val condition: ConditionsDto,

    @SerializedName("wind_kph")
    val wind: Float,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("cloud")
    val cloud: Int,

    @SerializedName("feelslike_c")
    val feelsLike: Float,
)
