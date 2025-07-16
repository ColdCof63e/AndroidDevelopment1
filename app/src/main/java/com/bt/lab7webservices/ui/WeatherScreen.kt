package com.bt.lab7webservices.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.bt.lab7webservices.viewmodel.WeatherState
import com.bt.lab7webservices.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel, apiKey: String, modifier: Modifier = Modifier) {
    var city by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Enter city") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                if (city.isNotBlank()) viewModel.fetchWeather(city, apiKey)
            }
        ) {
            Text("Get Weather")
        }
        Spacer(modifier = Modifier.height(24.dp))

        when (state) {
            is WeatherState.Loading -> CircularProgressIndicator()
            is WeatherState.Success -> {
                val data = (state as WeatherState.Success).weather
                Text("City: ${data.name}")
                Text("Temperature: ${data.main.temp}°C")
                Text("Condition: ${data.weather.firstOrNull()?.main}")
                Text("Description: ${data.weather.firstOrNull()?.description}")
            }
            is WeatherState.Error -> Text(
                "Error: ${(state as WeatherState.Error).message}",
                color = MaterialTheme.colorScheme.error
            )
            else -> {}
        }
    }
}