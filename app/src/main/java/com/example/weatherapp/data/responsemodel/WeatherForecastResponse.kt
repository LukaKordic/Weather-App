package com.example.weatherapp.data.responsemodel

import com.example.weatherapp.data.common.formatTemperature
import com.example.weatherapp.data.common.formatTime
import com.example.weatherapp.data.location.Location
import com.example.weatherapp.domain.WeatherForecast
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastResponse(
  val latitude: Float,
  val longitude: Float,
  @SerialName("hourly_units") val hourlyUnits: HourlyUnits? = HourlyUnits(null),
  val hourly: Hourly? = Hourly(emptyList(), emptyList()),
)

@Serializable
data class HourlyUnits(@SerialName("temperature_2m") val temperature: String?)

@Serializable
data class Hourly(
  val time: List<String>,
  @SerialName("temperature_2m") val temperature: List<Float>,
)

fun WeatherForecastResponse.mapToDomainModel(): WeatherForecast {
  val times = hourly?.time?.take(40)?.map { formatTime(it) } ?: emptyList()
  val temperatures =
    hourly?.temperature?.take(40)?.map { formatTemperature(value = it, unit = hourlyUnits?.temperature ?: "℃") }
      ?: emptyList()

  return WeatherForecast(
    location = Location(latitude, longitude),
    hourlyTemperature = times.zip(temperatures)
  )
}
