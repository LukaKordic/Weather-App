package com.example.weatherapp.ui.forecast.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.ui.forecast.presentation.ForecastViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun ForecastScreen() {
  val viewModel: ForecastViewModel = viewModel()
  val uiState = viewModel.uiState
  viewModel.loadWeatherForecast()

  Column {
    CurrentWeather(currentWeather = uiState.data.currentWeather)
    CurrentLocation(uiState.data.location)
    HourlyForecastSlider(weatherForecast = uiState.data)
    if (uiState.loading) CircularProgressIndicator()
    if (uiState.error != null) Snackbar {
      // TODO: 02.07.2023. Implement error display
    }
  }
}

@Preview
@Composable
fun PreviewForecastScreen() {
  WeatherAppTheme {
    ForecastScreen()
  }
}
