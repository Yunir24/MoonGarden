package com.dauto.data.network.models

data class DayWithHoursDto(
    val date: String,
    val day: DayWeatherDto,
    val astro: AstroDto,
    val hour: List<HoursWeatherDto>
)
