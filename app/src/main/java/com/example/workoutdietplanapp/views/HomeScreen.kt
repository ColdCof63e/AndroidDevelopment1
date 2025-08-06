package com.example.workoutdietplanapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.workoutdietplanapp.R
import com.example.workoutdietplanapp.navigation.Route
import com.example.workoutdietplanapp.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, userViewModel: UserViewModel) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    var showLogoutDialog by remember { mutableStateOf(false) }
    var selectedExercise by remember { mutableStateOf<String?>(null) }

    val user by userViewModel.user.collectAsState()
    val workouts by userViewModel.workouts.collectAsState()
    val exercises = listOf("Chest", "Back", "Shoulders", "Biceps", "Triceps", "Legs")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "💪 Stronger'n Better 💪",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.background(
                    Brush.horizontalGradient(
                        colors = listOf(Color(0xFF2196f3), Color(0xFF3f51b5))
                    )
                ),
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.FitnessCenter, contentDescription = "Home", tint = Color.White) },
                    label = { Text("Workouts", color = Color.White) },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate(Route.Home.routeName)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.FitnessCenter, contentDescription = "Diet Plan", tint = Color.White) },
                    label = { Text("Diet Plan", color = Color.White) },
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        navController.navigate(Route.DietPlan.routeName)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.White) },
                    label = { Text("Profile", color = Color.White) },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                        navController.navigate(Route.Profile.routeName)}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Logout, contentDescription = "Logout", tint = Color.White) },
                    label = { Text("Logout", color = Color.White) },
                    selected = false,
                    onClick = { showLogoutDialog = true }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.home_screen),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Foreground Content
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    Text(
                        text = "Welcome, ${user.name}!",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                if(selectedExercise == null) {
                    items (exercises) { exercise ->
                        val exerciseWorkouts = workouts.filter { it.workout == exercise }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White.copy(
                                    alpha = 0.1f
                                )
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedExercise = exercise }
                                    .height(150.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = getExerciseImage(exercise)),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier.fillMaxSize()
                                )
                                if(exerciseWorkouts.isNotEmpty()) {
                                    Text(
                                        "${exerciseWorkouts[0].day}\n${ exercise }",
                                        color = Color.White,
                                        modifier = Modifier
                                            .padding(2.dp)
                                            .background(
                                            Color.Black.copy(alpha = 0.5f),
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                    )
                                }
                            }
                        }
                    }
                } else {
                    // Optionally, add a 'Back' button
                    item {
                        TextButton(onClick = { selectedExercise = null }) {
                            Text("← Back to Exercises", color = Color.White)
                        }
                    }
                    if (workouts.isNotEmpty()) {
//                        items(workouts.size) { index ->
//                            val w = workouts[index]
//                        }
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.1f)),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
//                                    Text("  →${w.variations} variations x ${w.reps} reps @ ${w.maxWeightKg}kg", color = Color.White)
                                    Text(
                                        "Beginner:\n3 Variations x 3 reps @ 15kg" +
                                                "\nIntermediate:\n3 Variations x 5 reps @ 20kg" +
                                                "\nAdvance:\n5 Variations x 5 reps @ 35kg"
                                    )
                                }
                            }
                        }
                    } else {
                        item {
                            Text(
                                text = "No workouts available for $selectedExercise.",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }

    // Logout Dialog
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Confirm Logout") },
            text = { Text("Are you sure you want to log out?") },
            confirmButton = {
                TextButton(onClick = {
                    showLogoutDialog = false
                    userViewModel.logout()
                    navController.navigate(Route.SignIn.routeName) {
                        popUpTo(0)
                    }
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

fun getExerciseImage(exercise: String): Int {
    return when (exercise) {
        "Chest" -> R.drawable.chest
        "Back" -> R.drawable.back
        "Shoulders" -> R.drawable.shoulders
        "Biceps" -> R.drawable.biceps
        "Triceps" -> R.drawable.triceps
        "Legs" -> R.drawable.legs
        else -> R.drawable.welcome_bg
    }
}