package com.bt.sharedPreferences.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bt.sharedPreferences.nav.Route
import com.bt.sharedPreferences.viewmodels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, userViewModel: UserViewModel) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Column {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value =  userViewModel.userSelection.value.email,
                onValueChange = { userViewModel.updateSelection(email = it) },
                label = { Text("Email") }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Variables
        val context = LocalContext.current

        // Variable for Dropdown
        val provinces = arrayOf("ON", "AB", "BC", "MB", "NB", "NL", "NT", "NS", "NU", "PE", "QC", "SK", "YT")
        var selectedProvince by remember { mutableStateOf(false)}
        var selectedProvinceIndex by remember { mutableStateOf(0)}

        // Defining Dropdown
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(16.dp))

            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    selectedProvince = true
                }
            ) {
                Text("Province: ${provinces[selectedProvinceIndex]}")
            }
        }

        // Displaying DropdownMenu and button
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = selectedProvince,
            onDismissRequest = {
                selectedProvince = false
            }
        ) {
            provinces.forEachIndexed { index, province ->
                DropdownMenuItem(
                    text = {Text(province)},
                    onClick = {
                        selectedProvince = false
                        selectedProvinceIndex = index
                        userViewModel.updateSelection(province = provinces[index])
                    }

                )
            }
        }

        // Variables for Checkbox
        val stocks = listOf("Stocks 1", "Stocks 2", "Stocks 3", "Stocks 4")
        val checkedStocks = remember { mutableStateListOf(false, false, false, false)}
        var selectedStocks = stocks.filterIndexed { index, _ -> checkedStocks[index] }

        // Checkbox
        Column{
            Text("Stocks")
            stocks.forEachIndexed { index, stock ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkedStocks[index],
                        onCheckedChange = {
                            checkedStocks[index] = it
                            selectedStocks = stocks.filterIndexed { i, _ -> checkedStocks[i] }
                            userViewModel.updateSelection(stocks = selectedStocks)
                            Toast.makeText(context, "${if (checkedStocks[index]) "checked" else "unchecked"} $stock", Toast.LENGTH_SHORT).show()
                        }
                    )
                    Text(stock, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

        // Variables for Radio Button
        val subscription = listOf("Monthly", "Weekly")
        var subscriptionStatus by remember { mutableStateOf("Monthly")}

        // Defining and Displaying Radio Button
        Column {
            Text("Subscription")
            subscription.forEach { option ->
                Row (verticalAlignment = Alignment.CenterVertically) {
                    // Defining Radio button
                    RadioButton(
                        selected = subscriptionStatus == option,
                        onClick = {
                            subscriptionStatus = option
                            userViewModel.updateSelection(subscription = option)
                            Toast.makeText(context, "You have selected $option Subscription", Toast.LENGTH_SHORT).show()
                        }
                    )
                    Text(option, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

        // Validation Button
        OutlinedButton(
            onClick = {
                val selectedStockList = stocks.filterIndexed { i, _ -> checkedStocks[i] }
                val emailPattern = android.util.Patterns.EMAIL_ADDRESS
                val email = userViewModel.userSelection.value.email
                val province = userViewModel.userSelection.value.province
                val subscription = userViewModel.userSelection.value.subscription

                if (email.isBlank() || !emailPattern.matcher(email).matches()) {
                    Toast.makeText(context, "Invalid email", Toast.LENGTH_SHORT).show()
                    return@OutlinedButton
                }

                if (province.isBlank()) {
                    Toast.makeText(context, "Please select a province", Toast.LENGTH_SHORT).show()
                    return@OutlinedButton
                }

                if (selectedStockList.isEmpty()) {
                    Toast.makeText(context, "Select at least one stock", Toast.LENGTH_SHORT).show()
                    return@OutlinedButton
                }

                if (subscription.isBlank()) {
                    Toast.makeText(context, "Please select a subscription", Toast.LENGTH_SHORT).show()
                    return@OutlinedButton
                }

                navController.navigate(Route.ResultScreen.routeTo)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Validate",
                style = MaterialTheme.typography.bodyLarge)
        }
    }
}