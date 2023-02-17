package com.example.weather.data.mappers

import com.example.weather.data.remote.models.common.HourDto
import com.example.weather.domain.mappers.Mapper
import com.example.weather.domain.models.Hour

class HourMapper : Mapper<HourDto, Hour> {        // dayDto,Day
    override fun map(input: HourDto): Hour {
        return Hour(
            input.time,
            currentTemp = input.temp_c,
            currentWindSpeed = input.wind_kph,
            currentHumidity = input.humidity,
            currentPressure = input.pressure_in,
            currentCloud = input.cloud,
            currentConditionImage = input.condition.icon
        )
    }
}