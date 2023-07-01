package com.example.weatherapp.ui.forecast.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.domain.WeatherForecast
import com.example.weatherapp.ui.forecast.presentation.ForecastViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun ForecastScreen() {
  val viewModel: ForecastViewModel = viewModel()
  viewModel.loadWeatherForecast()
    .collectAsStateWithLifecycle(initialValue = Result.success(WeatherForecast.EMPTY))
}

@Preview
@Composable
fun PreviewForecastScreen() {
  WeatherAppTheme() {
    ForecastScreen()
  }
}
