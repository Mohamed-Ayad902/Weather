@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.weather.domain.use_cases

import android.location.Location
import com.example.weather.common.Resource
import com.example.weather.data.location.LocationTracker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val locationTracker: LocationTracker
) {

    suspend operator fun invoke(): Flow<Resource<Location?>> = flow {
        emit(Resource.Loading())
        emit(locationTracker.getCurrentLocation())
    }

}