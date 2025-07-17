package com.bt.lab7webservices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bt.lab7webservices.ui.WeatherScreen
import com.bt.lab7webservices.viewmodel.WeatherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiKey = "18c0b5d0c0c97a5622a1419fb09dbe37"

        setContent {
            val viewModel = WeatherViewModel()
            WeatherScreen(viewModel, apiKey)
        }
    }
}