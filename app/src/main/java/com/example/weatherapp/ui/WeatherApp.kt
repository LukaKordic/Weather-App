package com.example.weatherapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weatherapp.ui.forecast.composables.ForecastScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherApp() {
  WeatherAppTheme {
    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize()) {
      ForecastScreen()
    }
  }
}
