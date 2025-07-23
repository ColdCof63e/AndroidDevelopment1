package com.bt.sharedPreferences.viewmodels

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.bt.sharedPreferences.models.UserSelection
import com.bt.sharedPreferences.utils.UserPrefs

class UserViewModel: ViewModel() {
    private val _userSelection = mutableStateOf(UserSelection())
    val userSelection: State<UserSelection> = _userSelection

    init {
        updateSelection()
    }

    fun updateSelection(
        email: String = _userSelection.value.email,
        province: String = _userSelection.value.province,
        stocks: List<String> = _userSelection.value.stocks,
        subscription: String = _userSelection.value.subscription

    ) {
        _userSelection.value = UserSelection(email, province, stocks, subscription)
    }

    fun loadFromPrefs(context: Context) {
        val prefs = UserPrefs(context)
        _userSelection.value = UserSelection(
            email = prefs.email,
            province = prefs.province,
            stocks = prefs.stocks,
            subscription = prefs.subscription
        )
    }

    fun saveToPrefs(context: Context) {
        val prefs = UserPrefs(context)
        prefs.email = _userSelection.value.email
        prefs.province = _userSelection.value.province
        prefs.stocks = _userSelection.value.stocks
        prefs.subscription = _userSelection.value.subscription
    }
}