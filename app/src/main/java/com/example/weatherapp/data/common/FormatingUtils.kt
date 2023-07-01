package com.example.weatherapp.data.common

import java.time.LocalDateTime
import java.time.format.DateTimeParseException

fun formatTime(time: String): String {
  return try {
    LocalDateTime.parse(time).toLocalTime().toString()
  } catch (parseException: DateTimeParseException) {
    parseException.printStackTrace()
    time
  }
}

fun formatTemperature(value: Float, unit: String): String {
  return "$value$unit"
}
