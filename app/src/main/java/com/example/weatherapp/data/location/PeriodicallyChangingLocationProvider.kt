package com.example.weatherapp.data.location

import com.example.weatherapp.data.common.testLocations
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
        println("PeriodicallyChangingLocationProvider.getLocation ${testLocations[i]}")
        delay(DELAY_PERIOD.seconds)
      }
    }
  }
}

private const val DELAY_PERIOD = 10
