package com.example.weatherapp.data.location

import kotlinx.coroutines.flow.Flow

interface LocationProvider {
  fun getLocation(): Flow<Location>
}
