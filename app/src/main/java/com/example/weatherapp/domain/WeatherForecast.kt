package com.example.weatherapp.domain

import com.example.weatherapp.data.location.Location

data class WeatherForecast(
  val location: Location,
  val hourlyTemperature: List<Pair<String, String>>,
) {

  companion object {
    val EMPTY = WeatherForecast(Location.ZERO, listOf())
  }
}
