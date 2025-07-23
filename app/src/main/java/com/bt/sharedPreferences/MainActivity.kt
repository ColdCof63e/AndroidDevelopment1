package com.bt.sharedPreferences

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.bt.sharedPreferences.nav.AppNavGraph
import com.bt.sharedPreferences.ui.theme.SharedPreferencesTheme
import com.bt.sharedPreferences.viewmodels.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SharedPreferencesTheme {
                val navController = rememberNavController()

                val userViewModel: UserViewModel = viewModel()

                AppNavGraph(navController = navController, viewModel = userViewModel)
            }
        }
    }
}