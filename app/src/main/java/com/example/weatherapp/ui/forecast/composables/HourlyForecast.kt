package com.example.weatherapp.ui.forecast.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.domain.model.HourForecast
import com.example.weatherapp.domain.model.WeatherForecast
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun HourlyForecastSlider(weatherForecast: WeatherForecast) {
  Surface(
    modifier = Modifier
      .padding(horizontal = 10.dp, vertical = 20.dp)
      .fillMaxWidth(),
    shape = RoundedCornerShape(20.dp),
    color = MaterialTheme.colorScheme.surfaceVariant
  ) {
    LazyRow(Modifier.padding(8.dp)) {
      items(weatherForecast.hourlyForecast.count()) { index ->
        HourlyForecastItem(weatherForecast.hourlyForecast[index])
      }
    }
  }
}

@Composable
fun HourlyForecastItem(forecast: HourForecast) {
  Column(modifier = Modifier.padding(8.dp)) {
    Text(text = forecast.time, modifier = Modifier.padding(8.dp))
    Text(text = forecast.temperature, modifier = Modifier.padding(start = 8.dp))
    Spacer(modifier = Modifier.size(20.dp))
    Image(
      painter = painterResource(id = forecast.icon),
      contentDescription = "Cloud cover icon",
      modifier = Modifier
        .padding(4.dp)
        .size(50.dp)
    )
    Text(text = forecast.cloudCover, modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center)
  }
}

@Preview
@Composable
fun HourlyForecastPreview() {
  WeatherAppTheme {
    HourlyForecastSlider(weatherForecast = WeatherForecast.PREVIEW)
  }
}
