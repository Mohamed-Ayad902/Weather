package com.example.weather.domain.location

import android.location.Location
import com.example.weather.common.Resource

interface ILocationService {
    suspend fun getCurrentLocation(): Resource<Location?>
}