package com.example.housekeeper.navigation

sealed class Routes(val route: String) {

    object Home : Routes("home")
    object Statistic : Routes("statistic")
    object Setting : Routes("setting")
    object Planing : Routes("planing")
}
