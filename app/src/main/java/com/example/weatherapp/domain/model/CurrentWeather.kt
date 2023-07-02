package com.example.weatherapp.domain.model

import androidx.annotation.DrawableRes
import com.example.weatherapp.R

data class CurrentWeather(
  val temperature: String,
  val isDay: Boolean,
  val weatherCode: Int,
  val windSpeed: String,
  @DrawableRes val icon: Int,
) {
  companion object {
    val EMPTY = CurrentWeather("20 ℃", true, 0, "0.0 km/h", R.drawable.sunny)
  }
}
