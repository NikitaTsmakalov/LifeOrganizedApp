package com.example.lifeorganizedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lifeorganizedapp.ui.screens.AddTaskScreen
import com.example.lifeorganizedapp.ui.screens.FirstLaunchManager
import com.example.lifeorganizedapp.ui.screens.MainScreen
import com.example.lifeorganizedapp.ui.screens.WelcomeScreen
import com.example.lifeorganizedapp.ui.theme.LifeOrganizedAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LifeOrganizedAppTheme {
                val navController = rememberNavController()
                val firstLaunchManager = FirstLaunchManager(this)

                NavHost(
                    navController = navController,
                    startDestination = if (firstLaunchManager.isFirstLaunch()) "welcome" else "main"
                ) {
                    composable("welcome") {
                        WelcomeScreen(
                            onNavigateToMain = {
                                firstLaunchManager.setFirstLaunchCompleted()
                                navController.navigate("main") {
                                    popUpTo("welcome") { inclusive = true }
                                }
                            }
                        )
                    }
                    composable("main") {
                        MainScreen(navController = navController)
                    }
                    composable("settings") {
                        SettingsScreen()
                    }
                    composable("general_settings") {
                        GeneralSettingsScreen()
                    }
                    composable("add_task") {
                        AddTaskScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Экран настроек (в разработке)")
    }
}

@Composable
fun GeneralSettingsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Экран общих настроек (в разработке)")
    }
}