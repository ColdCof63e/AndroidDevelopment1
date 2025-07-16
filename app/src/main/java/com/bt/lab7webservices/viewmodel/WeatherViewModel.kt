package com.bt.lab7webservices.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bt.lab7webservices.data.model.WeatherResponse
import com.bt.lab7webservices.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class WeatherState {
    object Idle: WeatherState()
    object Loading: WeatherState()

    data class Success(val weather: WeatherResponse): WeatherState()
    data class Error(val message: String): WeatherState()
}

class WeatherViewModel(val repository: WeatherRepository = WeatherRepository()): ViewModel() {
    val _state = MutableStateFlow<WeatherState>(WeatherState.Idle)
    val state: StateFlow<WeatherState> = _state

    fun fetchWeather(city: String, apiKey: String) {
        _state.value = WeatherState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getWeather(city, apiKey)
                _state.value = WeatherState.Success(response)
            } catch (e: Exception) {
                _state.value = WeatherState.Error(e.message ?: "Unknown error")
            }
        }
    }
}