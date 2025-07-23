package com.bt.sharedPreferences.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.bt.sharedPreferences.viewmodels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(navController: NavHostController, viewModel: UserViewModel) {
    Column(
        modifier = Modifier.padding(16.dp).padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Email: ${viewModel.userSelection.value.email}")
        Text("Province: ${viewModel.userSelection.value.province}")
        Text("Stocks: ${viewModel.userSelection.value.stocks.joinToString(", ")}")
        Text("Subscription: ${viewModel.userSelection.value.subscription}")
    }
}