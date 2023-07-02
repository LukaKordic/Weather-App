package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.common.formatTemperature
import com.example.weatherapp.data.common.formatTime
import com.example.weatherapp.data.location.Location
import com.example.weatherapp.data.location.LocationResolver
import com.example.weatherapp.data.responsemodel.WeatherForecastResponse
import com.example.weatherapp.data.responsemodel.mapToDomainModel
import com.example.weatherapp.domain.model.WeatherForecast
import javax.inject.Inject

class WeatherForecastMapper @Inject constructor(private val locationResolver: LocationResolver) {

  suspend fun mapToWeatherForecast(response: WeatherForecastResponse): WeatherForecast {
    with(response) {
      val place = locationResolver.getLocationFromCoordinates(latitude, longitude)
      val times = hourly?.time?.take(NUM_OF_HOURLY_ITEMS)?.map { formatTime(it) } ?: emptyList()
      val temperatures = hourly?.temperature?.take(NUM_OF_HOURLY_ITEMS)
        ?.map { formatTemperature(value = it, unit = hourlyUnits?.temperature) }
        ?: emptyList()

      return WeatherForecast(
        location = Location(latitude, longitude),
        hourlyTemperature = times.zip(temperatures),
        currentWeather = currentWeather.mapToDomainModel(),
        place = place
      )
    }
  }
}

private const val NUM_OF_HOURLY_ITEMS = 48

