package com.bt.lab7webservices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bt.lab7webservices.ui.WeatherScreen
import com.bt.lab7webservices.ui.theme.Lab7WebServicesTheme
import com.bt.lab7webservices.viewmodel.WeatherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab7WebServicesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val weatherViewModel: WeatherViewModel = viewModel()

                    WeatherScreen(
                        viewModel = weatherViewModel,
                        apiKey = "https://api.openweathermap.org/data/2.5/weather?q=Toronto,canada&APPID=18c0b5d0c0c97a5622a1419fb09dbe37",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}