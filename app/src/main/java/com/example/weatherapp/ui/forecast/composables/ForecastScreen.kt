package com.example.weatherapp.ui.forecast.composables

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    CurrentLocation(uiState.data.place)
    Spacer(modifier = Modifier.size(40.dp))
    HourlyForecastSlider(weatherForecast = uiState.data)
    if (uiState.loading) CircularProgressIndicator()
    if (uiState.error != null) Toast.makeText(LocalContext.current, uiState.error.message, Toast.LENGTH_SHORT).show()
  }
}

@Preview
@Composable
fun PreviewForecastScreen() {
  WeatherAppTheme {
    ForecastScreen()
  }
}
