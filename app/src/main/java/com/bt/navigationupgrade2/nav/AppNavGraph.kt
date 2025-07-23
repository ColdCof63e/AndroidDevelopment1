package com.bt.navigationupgrade2.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bt.navigationupgrade2.views.HomeScreen
import com.bt.navigationupgrade2.views.ResultScreen
import com.bt.navigationupgrade2.viewmodels.UserViewModel
import com.bt.navigationupgrade2.views.InfoScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(navController: NavHostController, viewModel: UserViewModel) {
    val items = listOf(
        NavItem.Info,
        NavItem.Home,
        NavItem.Result
    )

    // Get the current route for the top bar title
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Lab 6 - Narendra_N01690273") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.InfoScreen.routeTo,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Route.InfoScreen.routeTo) {
                InfoScreen(navController = navController, viewModel = viewModel)
            }
            composable(Route.HomeScreen.routeTo) {
                HomeScreen(navController= navController, userViewModel= viewModel)
            }

            composable(Route.ResultScreen.routeTo) {
                ResultScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}