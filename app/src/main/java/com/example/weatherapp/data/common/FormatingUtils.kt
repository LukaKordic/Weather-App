package com.example.weatherapp.data.common

import java.time.LocalDateTime
import java.time.format.DateTimeParseException

fun formatTime(time: String): String {
  return try {
    "${LocalDateTime.parse(time).toLocalTime()} h"
  } catch (parseException: DateTimeParseException) {
    parseException.printStackTrace()
    time
  }
}

fun formatTemperature(value: Float, unit: String? = DEFAULT_TEMP_UNIT) = "$value ${unit ?: DEFAULT_TEMP_UNIT}"

fun formatWindSpeed(value: Float?, unit: String? = DEFAULT_WINDSPEED_UNIT) = "$value ${unit ?: DEFAULT_WINDSPEED_UNIT}"

fun formatCloudCover(value: Int) = "$value %"

private const val DEFAULT_TEMP_UNIT = "℃"
private const val DEFAULT_WINDSPEED_UNIT = "km/h"
