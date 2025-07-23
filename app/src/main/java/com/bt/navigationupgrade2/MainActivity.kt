package com.bt.navigationupgrade2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.bt.navigationupgrade2.nav.AppNavGraph
import com.bt.navigationupgrade2.ui.theme.NavigationUpgrade2Theme
import com.bt.navigationupgrade2.viewmodels.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationUpgrade2Theme {
                val navController = rememberNavController()

                val userViewModel: UserViewModel = viewModel()

                AppNavGraph(navController = navController, viewModel = userViewModel)
            }
        }
    }
}