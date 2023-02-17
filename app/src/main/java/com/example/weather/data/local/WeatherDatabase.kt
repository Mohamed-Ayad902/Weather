package com.example.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weather.data.local.converters.Converters
import com.example.weather.domain.models.Weather

@TypeConverters(Converters::class)
@Database([Weather::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun dao(): WeatherDao
}