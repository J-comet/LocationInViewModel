package hs.project.locationinviewmodel.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import com.google.android.gms.location.*
import hs.project.locationinviewmodel.LocationModel

class LocationLiveData(context: Context) : LiveData<LocationModel>() {

    companion object {
        val locationRequest = LocationRequest.create().apply {
            interval = 1000 * 60 * 10
            fastestInterval = 1000 * 60 * 10
            priority = Priority.PRIORITY_HIGH_ACCURACY
            maxWaitTime = 1000 * 60 * 10
            smallestDisplacement = 1f
        }
    }

    private var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            p0.locations.forEach { location ->
                setLocationData(location)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    private fun setLocationData(location: Location) {
        value = LocationModel(
            longitude = location.longitude,
            latitude = location.latitude
        )
    }

    override fun onInactive() {
        super.onInactive()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.also {
                    setLocationData(it)
                }
            }
        startLocationUpdates()
    }
}