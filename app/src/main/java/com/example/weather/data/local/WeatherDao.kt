package com.example.weather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.domain.models.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert7DaysForecast(weather: Weather)

    @Query("select * from weather order by id desc limit 1")
    suspend fun get7DaysForecast(): Weather

    @Query("delete from weather")
    suspend fun deleteSavedItems()
}