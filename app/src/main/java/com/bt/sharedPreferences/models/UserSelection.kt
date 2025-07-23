package com.bt.sharedPreferences.models

data class UserSelection (
    var email: String = "",
    var province: String = "",
    var stocks: List<String> = emptyList(),
    var subscription: String = ""
)