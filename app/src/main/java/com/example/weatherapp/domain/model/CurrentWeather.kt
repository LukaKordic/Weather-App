package com.example.weatherapp.domain.model

data class CurrentWeather(
  val temperature: String,
  val isDay: Boolean,
  val weatherCode: Int,
  val windSpeed: String,
) {
  companion object {
    val EMPTY = CurrentWeather("20 ℃", true, 0, "0.0 km/h")
  }
}
