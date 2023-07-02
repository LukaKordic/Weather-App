package com.example.weatherapp.ui.forecast.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun CurrentLocation(place: String) {
  Column(modifier = Modifier.padding(start = 40.dp)) {
    Text(text = "Current location:", fontSize = 20.sp)
    Text(text = place, fontSize = 20.sp)
  }
}

@Preview
@Composable
fun PreviewCurrentLocation() {
  WeatherAppTheme {
    CurrentLocation("Test")
  }
}
