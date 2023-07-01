package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.ui.forecast.presentation.ForecastUiState
import com.example.weatherapp.ui.forecast.presentation.ForecastViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private val viewModel: ForecastViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.loadWeatherForecast()

    setContent {
      WeatherAppTheme {
        val uiState = viewModel.uiState
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Greeting(uiState)
          }
        }
      }
    }
  }
}

@Composable
fun Greeting(uiState: ForecastUiState, modifier: Modifier = Modifier) {
  Column {
    Text(
      text = "Current location is ${uiState.data.location}!",
      modifier = modifier
    )
    Text(
      text = "Hourly forecast: ${uiState.data.hourlyTemperature}!",
      modifier = modifier
    )
  }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  WeatherAppTheme {
    Greeting(ForecastUiState.LOADING)
  }
}
