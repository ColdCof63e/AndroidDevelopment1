package com.bt.sharedPreferences.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.bt.sharedPreferences.models.UserSelection

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
}