package com.bt.sharedPreferences.utils

import android.content.Context

class UserPrefs (context: Context) {
    private val sharedPreferences = context.getSharedPreferences("user_selection", Context.MODE_PRIVATE)

    var email: String
        get() = sharedPreferences.getString("email", "") ?: ""
        set(value) = sharedPreferences.edit().putString("email", value).apply()

    var province: String
        get() = sharedPreferences.getString("province", "") ?: ""
        set(value) = sharedPreferences.edit().putString("province", value).apply()

    var stocks: List<String>
        get() = sharedPreferences.getStringSet("stocks", emptySet())?.toList() ?: emptyList()
        set(value) = sharedPreferences.edit().putStringSet("stocks", value.toSet()).apply()

    var subscription: String
        get() = sharedPreferences.getString("subscription", "") ?: ""
        set(value) = sharedPreferences.edit().putString("subscription", value).apply()
}