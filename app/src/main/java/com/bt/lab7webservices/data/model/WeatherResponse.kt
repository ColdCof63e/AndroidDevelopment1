package com.bt.lab7webservices.data.model

data class WeatherResponse(
    val weather: List<Weather>,
    val base: String,
    val main: Main,
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
)
