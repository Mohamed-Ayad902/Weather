package com.example.weather.data.remote.api

import com.example.weather.common.Constants
import com.example.weather.data.remote.models.forecast.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

//    @GET("current.json")
//    suspend fun getCurrentWeather(
//        @Query("key") key: String = Constants.API_KEY,
//        @Query("q") latLng: String
//    ): Response<CurrentDto>

    @GET("forecast.json")
    suspend fun get7DaysForecast(
        @Query("key") key: String = Constants.API_KEY,
        @Query("q") latLng: String,
        @Query("days") days: Int = 7
    ): Response<WeatherDto>


}