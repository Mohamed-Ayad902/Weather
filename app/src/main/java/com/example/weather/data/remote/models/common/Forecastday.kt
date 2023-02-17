package com.example.weather.data.remote.models.common

data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: DayDto,
    val hour: List<HourDto>
)