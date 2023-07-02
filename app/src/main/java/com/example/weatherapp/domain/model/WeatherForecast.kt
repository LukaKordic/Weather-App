package com.example.weatherapp.domain.model

import com.example.weatherapp.data.location.Location

data class WeatherForecast(
  val location: Location,
  val hourlyForecast: List<HourForecast>,
  val currentWeather: CurrentWeather,
  val place: String,
) {

  companion object {
    val PREVIEW = WeatherForecast(Location.ZERO, emptyList(), CurrentWeather.EMPTY, "Place")
  }
}
