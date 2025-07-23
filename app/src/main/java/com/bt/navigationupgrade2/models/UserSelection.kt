package com.bt.navigationupgrade2.models

data class UserSelection (
    var email: String = "",
    var province: String = "",
    var stocks: List<String> = emptyList(),
    var subscription: String = ""
)