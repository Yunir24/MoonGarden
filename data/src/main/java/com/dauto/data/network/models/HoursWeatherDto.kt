package com.dauto.data.network.models

import com.google.gson.annotations.SerializedName

data class HoursWeatherDto (

    @SerializedName("time")
    val time: String,
    @SerializedName("temp_c")
    val temperature: String,
    @SerializedName("condition")
    val condition: ConditionsDto
)