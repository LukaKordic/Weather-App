package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.OpenMeteoApi
import com.example.weatherapp.data.location.Location
import com.example.weatherapp.data.location.LocationProvider
import com.example.weatherapp.data.responsemodel.mapToDomainModel
import com.example.weatherapp.domain.model.WeatherForecast
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ForecastRepository @Inject constructor(
  private val apiService: OpenMeteoApi,
  private val locationProvider: LocationProvider,
) {

  private val _forecastFlow =
    MutableSharedFlow<Result<WeatherForecast>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
  val forecastFlow: SharedFlow<Result<WeatherForecast>> = _forecastFlow

  init {
    observeLocationChanges()
  }

  @OptIn(DelicateCoroutinesApi::class)
  private fun observeLocationChanges() {
    locationProvider.getLocation()
      .onEach { getForecastForLocation(it) }
      .launchIn(GlobalScope)
  }

  private suspend fun getForecastForLocation(location: Location) {
    try {
      val result = apiService.getWeatherForLocation(location.latitude, location.longitude)
      if (result.isSuccessful) {
        result.body()?.let { _forecastFlow.tryEmit(Result.success(it.mapToDomainModel())) }
      } else {
        result.errorBody()?.use {
          _forecastFlow.tryEmit(Result.failure(Throwable(message = result.message())))
        }
      }
    } catch (e: Exception) {
      _forecastFlow.tryEmit(Result.failure(e))
    }
  }
}
