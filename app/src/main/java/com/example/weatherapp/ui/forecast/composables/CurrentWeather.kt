package com.example.weatherapp.ui.forecast.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun CurrentWeather(currentWeather: CurrentWeather) {
  Surface(modifier = Modifier.fillMaxWidth()) {
    Row(modifier = Modifier.padding(40.dp), horizontalArrangement = Arrangement.SpaceBetween) {
      Column {
        Text(text = currentWeather.temperature, fontSize = 48.sp)
        Text(
          text = currentWeather.windSpeed,
          fontSize = 18.sp,
          textAlign = TextAlign.Center,
          modifier = Modifier.padding(top = 8.dp)
        )
      }
      Image(
        imageVector = Icons.Default.Star, contentDescription = "Star",
        modifier = Modifier.size(80.dp),
        colorFilter = ColorFilter.tint(Yellow)
      )
    }
  }
}

@Preview
@Composable
fun PreviewCurrentWeather() {
  WeatherAppTheme {
    CurrentWeather(CurrentWeather.EMPTY)
  }
}
