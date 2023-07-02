package com.example.weatherapp.domain.model

import com.example.weatherapp.data.location.Location

data class WeatherForecast(
  val location: Location,
  val hourlyTemperature: List<Pair<String, String>>,
  val currentWeather: CurrentWeather,
  val place: String,
) {

  companion object {
    val EMPTY = WeatherForecast(Location.ZERO, listOf(), CurrentWeather.EMPTY, "Place")
  }
}
