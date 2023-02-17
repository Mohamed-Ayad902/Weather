package com.example.weather.presentation.home_screen

import com.example.weather.domain.models.Weather

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val weather: Weather? = null
)