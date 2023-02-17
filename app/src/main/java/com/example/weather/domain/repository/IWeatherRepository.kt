package com.example.weather.domain.repository

import com.example.weather.common.Resource
import com.example.weather.domain.models.Weather
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {

    suspend fun get7DaysForecast(latLng: String): Flow<Resource<Weather>>

}