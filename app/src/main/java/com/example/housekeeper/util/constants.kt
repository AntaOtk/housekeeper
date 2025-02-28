package com.example.housekeeper.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.Color
import com.example.housekeeper.R
import com.example.housekeeper.data.bd.CategoryEntity
import com.example.housekeeper.navigation.BottomNavItem
import com.example.housekeeper.navigation.Routes

object Constants {
    const val accountTransferData = "data_account"
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
            label = "Transaction",
            icon = Icons.Filled.AddShoppingCart,
            route = Routes.AddTransaction.route
        ),
        BottomNavItem(
            label = "Setting",
            icon = Icons.Filled.Settings,
            route = Routes.Setting.route
        ),
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

    val firstCategoryList = listOf(
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

