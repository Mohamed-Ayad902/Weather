package com.example.weather.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val latLng: String,
    val cityName: String,
    val cityDetails: String,

    val currentCondition: String,
    val currentConditionImage: String,

    val currentTemp: Double,
    val currentWindSpeed: Double,
    val currentHumidity: Int,
    val currentPressure: Double,
    val currentCloud: Int,

    val forecastDays: List<ForecastDay>
)

data class ForecastDay(
    val dayDate: String,
    val dayMaxTem: Double,
    val dayMinTem: Double,
    val dayAvgWind: Double,
    val dayAvgHumidity: Double,
    val currentCondition: String,
    val currentConditionImage: String,
    val hours: List<Hour>
)

data class Hour(
    val time: String,
    val currentTemp: Double,
    val currentWindSpeed: Double,
    val currentHumidity: Int,
    val currentPressure: Double,
    val currentCloud: Int,
    val currentConditionImage: String,
)