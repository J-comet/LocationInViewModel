package hs.project.locationinviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import hs.project.locationinviewmodel.util.LocationLiveData

class HomeVM(application: Application) : AndroidViewModel(application) {

    private val locationData = LocationLiveData(application)

    fun getLocationData() = locationData
}