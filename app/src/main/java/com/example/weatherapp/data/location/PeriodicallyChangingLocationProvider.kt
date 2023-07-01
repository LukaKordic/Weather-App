package com.example.weatherapp.data.location

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds

@Singleton
class PeriodicallyChangingLocationProvider @Inject constructor() : LocationProvider {
  override fun getLocation(): Flow<Location> = flow {
    while (true) {
      for (i in 0 until 10) {
        emit(testLocations[i])
        println(testLocations[i])
        delay(10.seconds)
      }
    }
  }
}