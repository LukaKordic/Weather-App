package com.example.weatherapp.data.responsemodel

import com.example.weatherapp.data.common.formatTemperature
import com.example.weatherapp.data.common.formatTime
import com.example.weatherapp.data.location.Location
import com.example.weatherapp.domain.model.WeatherForecast
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastResponse(
  val latitude: Float,
  val longitude: Float,
  @SerialName("hourly_units") val hourlyUnits: HourlyUnits? = HourlyUnits(null),
  val hourly: Hourly? = Hourly(emptyList(), emptyList()),
  @SerialName("current_weather") val currentWeather: CurrentWeatherResponse? = null,
)

fun WeatherForecastResponse.mapToDomainModel(): WeatherForecast {
  val times = hourly?.time?.take(NUM_OF_HOURLY_ITEMS)?.map { formatTime(it) } ?: emptyList()
  val temperatures =
    hourly?.temperature?.take(NUM_OF_HOURLY_ITEMS)
      ?.map { formatTemperature(value = it, unit = hourlyUnits?.temperature) }
      ?: emptyList()

  return WeatherForecast(
    location = Location(latitude, longitude),
    hourlyTemperature = times.zip(temperatures),
    currentWeather = currentWeather.mapToDomainModel()
  )
}

private const val NUM_OF_HOURLY_ITEMS = 48
