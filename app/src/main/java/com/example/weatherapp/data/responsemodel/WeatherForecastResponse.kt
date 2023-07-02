package com.example.weatherapp.data.responsemodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastResponse(
  val latitude: Double,
  val longitude: Double,
  @SerialName("hourly_units") val hourlyUnits: HourlyUnits? = null,
  val hourly: Hourly? = null,
  @SerialName("current_weather") val currentWeather: CurrentWeatherResponse? = null,
)

