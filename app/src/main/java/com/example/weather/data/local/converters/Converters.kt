package com.example.weather.data.local.converters

import androidx.room.TypeConverter
import com.example.weather.domain.models.ForecastDay
import com.example.weather.domain.models.Hour
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun toDayList(data: String): List<ForecastDay> {
        val listType = object : TypeToken<List<ForecastDay>>() {}.type
        return GsonBuilder().create().fromJson(data, listType)
    }

    @TypeConverter
    fun toDayString(list: List<ForecastDay>): String {
        return GsonBuilder().create().toJson(list)
    }

    @TypeConverter
    fun toHourList(data: String): List<Hour> {
        val listType = object : TypeToken<List<Hour>>() {}.type
        return GsonBuilder().create().fromJson(data, listType)
    }

    @TypeConverter
    fun toHourString(list: List<Hour>): String {
        return GsonBuilder().create().toJson(list)

    }

}