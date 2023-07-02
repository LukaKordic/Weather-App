package com.example.weatherapp.data.responsemodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hourly(
  val time: List<String>,
  @SerialName("temperature_2m") val temperature: List<Float>,
)
