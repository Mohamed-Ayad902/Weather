package com.example.weather.data.repository

import com.example.weather.common.Resource
import com.example.weather.data.local.WeatherDao
import com.example.weather.data.mappers.ForecastMapper
import com.example.weather.data.remote.api.WeatherApi
import com.example.weather.domain.models.Weather
import com.example.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val dao: WeatherDao,
    private val api: WeatherApi,
    private val forecastMapper: ForecastMapper
) : IWeatherRepository {

    override suspend fun get7DaysForecast(latLng: String): Flow<Resource<Weather>> = flow {
        val cachedForecast = dao.get7DaysForecast()
        emit(Resource.Loading(cachedForecast))

        try {
            val response = api.get7DaysForecast(latLng = latLng)
            if (response.isSuccessful) {
                response.body()?.let {
                    dao.deleteSavedItems()
                    dao.insert7DaysForecast(forecastMapper.map(it))
                }
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = e.message ?: "Oops, http exceptions",
                    data = cachedForecast
                )
            )
        } catch (e: IOException) {
            Resource.Error(
                message = "Couldn't reach the server, check your internet connection.",
                data = cachedForecast
            )
        } catch (e: Exception) {
            Resource.Error(
                message = e.message ?: "unKnown Exception",
                data = cachedForecast
            )
        }

        val newForecast = dao.get7DaysForecast()
        emit(Resource.Success(newForecast))
    }
}