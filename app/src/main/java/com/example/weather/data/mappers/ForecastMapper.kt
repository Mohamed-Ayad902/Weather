package com.example.weather.data.mappers

import com.example.weather.data.remote.models.forecast.WeatherDto
import com.example.weather.domain.mappers.Mapper
import com.example.weather.domain.models.Weather
import javax.inject.Inject
import kotlin.math.roundToInt

class ForecastMapper @Inject constructor(
    private val forecastDayMapper: ForecastDayMapper
) : Mapper<WeatherDto, Weather> {

    override fun map(input: WeatherDto): Weather {
        return Weather(
            id = null,
            latLng = "${roundTwoDecimals(input.location.lat)},${roundTwoDecimals(input.location.lon)}",
            cityName = input.location.name,
            cityDetails = "${input.location.region} - ${input.location.country}",
            currentCondition = input.current.condition.text,
            currentConditionImage = input.current.condition.icon,
            currentTemp = input.current.temp_c,
            currentWindSpeed = input.current.wind_kph,
            currentHumidity = input.current.humidity,
            currentPressure = input.current.pressure_in,
            currentCloud = input.current.cloud,
            forecastDays = input.forecast.forecastday.map { forecastDayMapper.map(it) }
        )
    }

    private fun roundTwoDecimals(value: Double) =    // rounds from 31.027 to 31.03
        (value * 100.0).roundToInt() / 100.0
}