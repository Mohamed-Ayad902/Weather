package com.example.weather.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.weather.common.Resource
import com.example.weather.domain.location.ILocationService
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

@ExperimentalCoroutinesApi
class LocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : ILocationService {

    override suspend fun getCurrentLocation(): Resource<Location?> {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission)
            return Resource.Error("Please grant required permissions")
        if (!isGpsEnabled)
            return Resource.Error("Please open GPS to continue")

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        cont.resume(Resource.Success(result))
                    } else {
                        cont.resume(Resource.Error(message = "Failed to get last known location"))
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(Resource.Success(it))
                }
                addOnFailureListener {
                    cont.resume(Resource.Error(it.message.toString()))
                }
                addOnCanceledListener {
                    cont.cancel()
                    Resource.Error(message = "Canceled", data = null)
                }
            }
        }
    }

}