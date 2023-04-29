package com.dauto.data.network

import com.dauto.data.network.models.WeatherItemDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {


    @Headers("Content-Type: application/json")
    @GET("forecast.json")
    suspend fun getCurrentWeather(
        @Query("q")q: String,
        @Query("lang") lang: String,
        @Query("key") key: String,
        @Query("days") days: String
    ): WeatherItemDto


}