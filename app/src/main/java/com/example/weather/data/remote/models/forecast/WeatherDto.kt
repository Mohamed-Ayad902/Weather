package com.example.weather.data.remote.models.forecast

import com.example.weather.data.remote.models.common.Current
import com.example.weather.data.remote.models.common.Location


data class WeatherDto(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)