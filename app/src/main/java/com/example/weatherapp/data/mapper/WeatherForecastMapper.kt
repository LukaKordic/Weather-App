package com.example.weatherapp.data.mapper

import com.example.weatherapp.R
import com.example.weatherapp.data.common.formatCloudCover
import com.example.weatherapp.data.common.formatTemperature
import com.example.weatherapp.data.common.formatTime
import com.example.weatherapp.data.location.Location
import com.example.weatherapp.data.location.LocationResolver
import com.example.weatherapp.data.responsemodel.WeatherForecastResponse
import com.example.weatherapp.data.responsemodel.mapToDomainModel
import com.example.weatherapp.domain.model.HourForecast
import com.example.weatherapp.domain.model.WeatherForecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherForecastMapper @Inject constructor(private val locationResolver: LocationResolver) {

  suspend fun mapToWeatherForecast(response: WeatherForecastResponse): WeatherForecast =
    withContext(Dispatchers.Default) {
      with(response) {
        val place = locationResolver.getLocationFromCoordinates(latitude, longitude)
        val times = hourly?.time?.take(NUM_OF_HOURLY_ITEMS)?.map { formatTime(it) } ?: emptyList()
        val temperatures = hourly?.temperature?.take(NUM_OF_HOURLY_ITEMS)
          ?.map { formatTemperature(value = it, unit = hourlyUnits?.temperature) } ?: emptyList()
        val clouds = hourly?.cloudCover?.take(NUM_OF_HOURLY_ITEMS)?.map { formatCloudCover(it) } ?: emptyList()
        val icons = cloudCoverageIcons()

        WeatherForecast(
          location = Location(latitude, longitude),
          hourlyForecast = buildList {
            for (i in 0 until NUM_OF_HOURLY_ITEMS) {
              add(
                HourForecast(
                  time = times[i],
                  temperature = temperatures[i],
                  cloudCover = clouds[i],
                  icon = icons[i]
                )
              )
            }
          }, currentWeather = currentWeather.mapToDomainModel(), place = place
        )
      }
    }

  private fun WeatherForecastResponse.cloudCoverageIcons(): List<Int> {
    return hourly?.cloudCover?.map {
      when (it) {
        in 0..10 -> R.drawable.sunny
        in 11..25 -> R.drawable.sun_with_clouds
        in 26..75 -> R.drawable.cloudy
        else -> R.drawable.full_clouds
      }
    } ?: emptyList()
  }
}

private const val NUM_OF_HOURLY_ITEMS = 48

