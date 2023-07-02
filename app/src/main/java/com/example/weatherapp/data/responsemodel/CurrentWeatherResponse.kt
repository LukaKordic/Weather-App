package com.example.weatherapp.data.responsemodel

import com.example.weatherapp.R
import com.example.weatherapp.data.common.formatTemperature
import com.example.weatherapp.data.common.formatWindSpeed
import com.example.weatherapp.domain.model.CurrentWeather
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponse(
  val temperature: Float? = null,
  val isDay: Boolean = false,
  @SerialName("weathercode") val weatherCode: Int? = null,
  @SerialName("windspeed") val windSpeed: Float? = null,
)

fun CurrentWeatherResponse?.mapToDomainModel(): CurrentWeather {
  return if (this == null) {
    CurrentWeather.EMPTY
  } else {
    CurrentWeather(
      formatTemperature(value = temperature ?: 0.0f),
      isDay = true,
      weatherCode = weatherCode ?: 0,
      windSpeed = formatWindSpeed(windSpeed),
      icon = if (isDay) R.drawable.sunny else R.drawable.moon
    )
  }
}
