package com.example.weather.domain.use_cases

import com.example.weather.common.Resource
import com.example.weather.domain.models.Weather
import com.example.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Get7DaysForecastUseCase @Inject constructor(
    private val repository: IWeatherRepository
) {

    suspend operator fun invoke(latLng: String): Flow<Resource<Weather>>  =
        repository.get7DaysForecast(latLng)

}