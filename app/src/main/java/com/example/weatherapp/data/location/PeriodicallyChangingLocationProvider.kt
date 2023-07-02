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
        delay(DELAY_PERIOD.seconds)
      }
    }
  }
}

private const val DELAY_PERIOD = 10

private val testLocations = listOf(
  Location(53.61965, 10.079969),
  Location(53.080917, 8.847533),
  Location(52.378384, 9.794862),
  Location(52.496384, 13.444041),
  Location(53.866863, 10.739542),
  Location(54.304540, 10.152741),
  Location(54.797276, 9.491039),
  Location(52.42641, 10.821392),
  Location(53.54279, 8.613462),
  Location(53.141598, 8.242565),
)
