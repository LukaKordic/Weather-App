package com.example.weatherapp.ui.forecast.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.ForecastRepository
import com.example.weatherapp.domain.WeatherForecast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val forecastRepo: ForecastRepository) : ViewModel() {

  var uiState by mutableStateOf(ForecastUiState.LOADING)
    private set

  fun loadWeatherForecast() {
    forecastRepo.forecastFlow
      .onEach { updateUiState(it) }
      .launchIn(viewModelScope)
  }

  private fun updateUiState(forecastResult: Result<WeatherForecast>) {
    forecastResult.onSuccess {
      uiState = ForecastUiState(loading = false, error = null, data = it)
      println(it)
    }
    forecastResult.onFailure {
      uiState = uiState.copy(error = it)
      println(it)
    }
  }
}
