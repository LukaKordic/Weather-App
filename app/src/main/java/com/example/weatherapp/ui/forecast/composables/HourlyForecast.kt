package com.example.weatherapp.ui.forecast.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapp.domain.model.WeatherForecast

@Composable
fun HourlyForecastSlider(weatherForecast: WeatherForecast) {
  Surface(
    modifier = Modifier
      .padding(horizontal = 10.dp, vertical = 20.dp)
      .fillMaxWidth()
      .height(200.dp),
    shape = RoundedCornerShape(20.dp),
    color = Color.Blue.copy(alpha = 0.2F)
  ) {
    LazyRow {
      items(weatherForecast.hourlyTemperature.count()) { index ->
        HourlyForecastItem(weatherForecast.hourlyTemperature[index])
      }
    }
  }
}

@Composable
fun HourlyForecastItem(forecast: Pair<String, String>) {
  Column(modifier = Modifier.padding(8.dp)) {
    Text(text = forecast.first, modifier = Modifier.padding(8.dp))
    Text(text = forecast.second, modifier = Modifier.padding(8.dp))
  }
}
