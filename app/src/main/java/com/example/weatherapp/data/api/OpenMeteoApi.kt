package com.example.weatherapp.data.api

import com.example.weatherapp.data.responsemodel.WeatherForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteoApi {

  @GET("forecast")
  suspend fun getWeatherForLocation(
    @Query("latitude") latitude: Double,
    @Query("longitude") longitude: Double,
    @Query("hourly") hourly: String = "temperature_2m,cloudcover_low",
    @Query("current_weather") showCurrentWeather: Boolean = true,
  ): Response<WeatherForecastResponse>
}
