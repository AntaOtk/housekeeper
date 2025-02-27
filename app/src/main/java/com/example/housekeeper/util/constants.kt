package com.example.housekeeper.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.Color
import com.example.housekeeper.navigation.BottomNavItem
import com.example.housekeeper.navigation.Routes

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.CurrencyExchange,
            route = Routes.Home.route
        ),
        BottomNavItem(
            label = "Statistic",
            icon = Icons.Filled.BarChart,
            route = Routes.Statistic.route
        ),
        BottomNavItem(
            label = "Setting",
            icon = Icons.Filled.Settings,
            route = Routes.Setting.route
        )

    )

    val GRAPH_COLOR = listOf(
        Color.Red,
        Color.Green,
        Color.DarkGray,
        Color.Magenta,
        Color.LightGray,
        Color.Unspecified,
        Color.Yellow,
        Color.Cyan,
        Color.Gray,
        Color.Blue,
    )
}

