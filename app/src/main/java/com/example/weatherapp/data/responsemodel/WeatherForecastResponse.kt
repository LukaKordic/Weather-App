package com.example.weatherapp.data.responsemodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastResponse(
  val latitude: Double,
  val longitude: Double,
  @SerialName("hourly_units") val hourlyUnits: HourlyUnits? = HourlyUnits(null),
  val hourly: Hourly? = Hourly(emptyList(), emptyList()),
  @SerialName("current_weather") val currentWeather: CurrentWeatherResponse? = null,
)

