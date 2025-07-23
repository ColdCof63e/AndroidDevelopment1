package com.bt.sharedPreferences.nav

sealed class Route(val routeTo: String) {
    object InfoScreen: Route("info_screen")
    object HomeScreen: Route("home_screen")
    object ResultScreen: Route("result_screen")
}