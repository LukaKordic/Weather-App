package com.example.weatherapp.ui.forecast.presentation

import com.example.weatherapp.domain.model.WeatherForecast

data class ForecastUiState(
  val loading: Boolean,
  val error: Throwable?,
  val data: WeatherForecast,
) {

  companion object {
    val LOADING = ForecastUiState(loading = true, error = null, data = WeatherForecast.EMPTY)
  }
}
