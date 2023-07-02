package com.example.weatherapp.data.location

import android.content.Context
import android.location.Geocoder
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationResolver @Inject constructor(@ApplicationContext context: Context) {

  private val geocoder by lazy { Geocoder(context) }

  suspend fun getLocationFromCoordinates(latitude: Double, longitude: Double): String =
    withContext(Dispatchers.Default) {
      geocoder.getFromLocation(latitude, longitude, 1)?.first()?.locality ?: "Location unknown"

    }
}
