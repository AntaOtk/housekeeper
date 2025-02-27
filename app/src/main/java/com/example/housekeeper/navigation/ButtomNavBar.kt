package com.example.housekeeper.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.housekeeper.R
import com.example.housekeeper.util.Constants

@Composable
fun BottomNavBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = colorResource(R.color.color_9)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Constants.BottomNavItems.forEach { navItem ->
            BottomNavigationItem(

                selected = currentRoute == navItem.route,

                onClick = {
                    navController.navigate(navItem.route)
                },

                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },

                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false
            )
        }
    }
}
