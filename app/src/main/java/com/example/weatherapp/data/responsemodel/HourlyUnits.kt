package com.example.weatherapp.data.responsemodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyUnits(@SerialName("temperature_2m") val temperature: String?)
