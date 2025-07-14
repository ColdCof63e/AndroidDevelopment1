package com.bt.lab7webservices.data.repository

import com.bt.lab7webservices.data.model.WeatherResponse
import com.bt.lab7webservices.data.network.RetrofitClient

class WeatherRepository {
    suspend fun getWeather(city: String, apiKey: String): WeatherResponse {
        return RetrofitClient.apiService.getWeatherData(city, apiKey)
    }
}