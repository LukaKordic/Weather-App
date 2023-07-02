package com.example.weatherapp.data.location

data class Location(val latitude: Double, val longitude: Double) {

  companion object {
    val ZERO = Location(0.0, 0.0)
  }
}

