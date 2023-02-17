package com.example.weather.presentation.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.common.Resource
import com.example.weather.domain.use_cases.Get7DaysForecastUseCase
import com.example.weather.domain.use_cases.GetCurrentLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeViewModel mohamed"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val get7DaysForecastUseCase: Get7DaysForecastUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    fun getForecast() {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentLocationUseCase().collectLatest { response ->
                Log.e(TAG, "getForecast: $response")
                when (response) {
                    is Resource.Error -> _state.update {
                        it.copy(
                            error = response.message
                                ?: "Couldn't get location... Make sure to grant permission and enable GPS.",
                            isLoading = false,
                            weather = null
                        )
                    }
                    is Resource.Loading -> _state.update {
                        it.copy(
                            isLoading = true,
                            error = null,
                            weather = null
                        )
                    }
                    is Resource.Success -> {
                        response.data?.let {
                            Log.e(
                                "mohamed",
                                "getForecast: ${it.latitude},${it.longitude}",
                            )
                            getData("${it.latitude},${it.longitude}")
                        } ?: kotlin.run {
                            Log.e(TAG, "getForecast: we nuuuuuuuuuul")
                            getData("30.01,29.0873")
                        }
                    }
                }
            }
        }
    }

    private suspend fun getData(latLng: String) {
        get7DaysForecastUseCase(latLng).collectLatest { response ->
            Log.w(TAG, "getData: $response")
            when (response) {
                is Resource.Error -> _state.update {
                    it.copy(
                        isLoading = false,
                        error = response.message ?: "Unknown error",
                        weather = response.data
                    )
                }
                is Resource.Loading -> _state.update {
                    it.copy(
                        isLoading = true,
                        error = null,
                        weather = response.data
                    )
                }
                is Resource.Success -> _state.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        weather = response.data
                    )
                }
            }
        }
    }

}