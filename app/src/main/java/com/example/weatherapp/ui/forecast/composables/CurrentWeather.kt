package com.example.weatherapp.ui.forecast.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun CurrentWeather(currentWeather: CurrentWeather) {
  Surface(modifier = Modifier.fillMaxWidth()) {
    Row(modifier = Modifier.padding(40.dp), horizontalArrangement = Arrangement.SpaceBetween) {
      Column {
        Text(text = currentWeather.temperature, fontSize = 48.sp)
        Row {
          Text(
            text = stringResource(R.string.wind_speed),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, end = 4.dp)
          )
          Text(
            text = currentWeather.windSpeed,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
          )
        }
      }
      Image(
        painter = painterResource(id = currentWeather.icon),
        contentDescription = stringResource(id = R.string.cd_day_night_icon),
        modifier = Modifier.size(80.dp)
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
