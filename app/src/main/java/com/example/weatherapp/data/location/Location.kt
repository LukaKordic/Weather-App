package com.example.weatherapp.data.location

data class Location(val latitude: Float, val longitude: Float) {

  companion object {
    val ZERO = Location(0.0F, 0.0F)
  }
}

