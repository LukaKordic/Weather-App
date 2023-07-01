package com.example.weatherapp.data.responsemodel

import com.example.weatherapp.domain.WeatherForecast
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastResponse(val latitude: Float, val longitude: Float) {

  fun mapToDomainModel() = WeatherForecast(temperature = "")
}
