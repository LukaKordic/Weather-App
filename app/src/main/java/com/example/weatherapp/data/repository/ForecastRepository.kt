package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.OpenMeteoApi
import com.example.weatherapp.data.location.Location
import com.example.weatherapp.data.location.LocationProvider
import com.example.weatherapp.data.responsemodel.mapToDomainModel
import com.example.weatherapp.domain.WeatherForecast
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ForecastRepository @Inject constructor(
  private val apiService: OpenMeteoApi,
  private val locationProvider: LocationProvider,
) {

  private val _forecastFlow =
    MutableSharedFlow<Result<WeatherForecast>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
  val forecastFlow: SharedFlow<Result<WeatherForecast>> = _forecastFlow

//  init {
//    getForecast()
//  }

  fun getForecast(): Flow<Result<WeatherForecast>> {
    return locationProvider.getLocation()
      .map { getForecastForLocation(it) }
  }

  private suspend fun getForecastForLocation(location: Location): Result<WeatherForecast> {
    return try {
      val result = apiService.getWeatherForLocation(location.latitude, location.longitude)
      if (result.isSuccessful) {
        result.body()?.let { Result.success(it.mapToDomainModel()) } ?: Result.success(WeatherForecast.EMPTY)
      } else {
        result.errorBody()?.let {
          Result.failure(Throwable(message = result.message()))
        } ?: Result.failure(Throwable(result.message()))
      }
    } catch (e: Exception) {
      Result.failure(e)
    }
  }
}
