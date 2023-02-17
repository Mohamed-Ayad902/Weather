package com.example.weather.data.mappers

import com.example.weather.data.remote.models.common.Forecastday
import com.example.weather.domain.mappers.Mapper
import com.example.weather.domain.models.ForecastDay
import javax.inject.Inject

class ForecastDayMapper @Inject constructor(
    private val hourMapper: HourMapper
) : Mapper<Forecastday, ForecastDay> {

    override fun map(input: Forecastday): ForecastDay {
        return ForecastDay(
            input.date,
            input.day.maxtemp_c,
            input.day.mintemp_c,
            input.day.avgvis_km,
            input.day.avghumidity,
            input.day.condition.text,
            input.day.condition.icon,
            input.hour.map { hourMapper.map(it) }
        )
    }
}