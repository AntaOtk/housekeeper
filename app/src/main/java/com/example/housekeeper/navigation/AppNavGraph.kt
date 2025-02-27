package com.example.housekeeper.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.housekeeper.presentation.main.MainScreen
import com.example.housekeeper.presentation.plan.PlaningScreen
import com.example.housekeeper.presentation.setting.SettingsScreen
import com.example.housekeeper.presentation.statistic.StatisticScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable(Routes.Home.route) {
            MainScreen()
        }
        composable(Routes.Statistic.route) {
            StatisticScreen()
        }
        composable(Routes.Setting.route) {
            SettingsScreen()
        }
        composable(Routes.Planing.route) {
            PlaningScreen()
        }
    }
}
