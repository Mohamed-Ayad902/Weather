package com.example.weather.data.remote.models.forecast

import com.example.weather.data.remote.models.common.Forecastday

data class Forecast(
    val forecastday: List<Forecastday>
)