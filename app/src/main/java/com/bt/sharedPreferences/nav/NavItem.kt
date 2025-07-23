package com.bt.sharedPreferences.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    object Info : NavItem("Info", Icons.Filled.Info, "info")
    object Home : NavItem("Home", Icons.Filled.Home, "home")
    object Result : NavItem("Result", Icons.Filled.CheckCircle, "result")
}