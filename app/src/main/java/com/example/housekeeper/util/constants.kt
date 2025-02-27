package com.example.housekeeper.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.housekeeper.R
import com.example.housekeeper.data.bd.CategoryEntity
import com.example.housekeeper.navigation.BottomNavItem
import com.example.housekeeper.navigation.Routes
import com.example.housekeeper.presentation.model.ChartModel

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

    val  firstCategoryList = listOf(
        CategoryEntity(
            null,
            "home",
            R.drawable.home,
            null,
        ),
        CategoryEntity(
            null,
            "transport",
            R.drawable.car_servise,
            null,
        ),
        CategoryEntity(
            null,
            "product",
            R.drawable.cosmetic,
            null,
        ),
        CategoryEntity(
            null,
            "restaurant",
            R.drawable.vaccines,
            null,
        ),
        CategoryEntity(
            null,
            "education",
            R.drawable.vaccines,
            null,
        ),
        CategoryEntity(
            null,
            "clothes",
            R.drawable.clothes,
            null,
        ),
        CategoryEntity(
            null,
            "pet",
            R.drawable.cosmetic,
            null,
        ),
    )
}

