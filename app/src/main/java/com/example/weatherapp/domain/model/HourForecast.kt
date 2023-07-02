package com.example.weatherapp.domain.model

import androidx.annotation.DrawableRes

data class HourForecast(
  val time: String,
  val temperature: String,
  val cloudCover: String,
  @DrawableRes val icon: Int,
)
