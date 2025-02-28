package com.example.housekeeper.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.housekeeper.domain.model.Expense
import com.example.housekeeper.presentation.add_transaction.AddTransactionScreen
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
            MainScreen() { arg1, arg2 ->
                navController.navigate(Routes.AddTransaction.route + "/$arg1" + "/$arg2")
            }
        }
        composable(Routes.Statistic.route) {
            StatisticScreen()
        }
        composable(Routes.Setting.route) {
            SettingsScreen(
                navigateToNewCategory = { navController.navigate(Routes.Planing.route) },
                navigateToNewTransaction = { navController.navigate(Routes.Planing.route) },
                navigateToPlan = { navController.navigate(Routes.Planing.route) })
        }
        composable(Routes.Planing.route) {
            PlaningScreen()
        }
        composable(Routes.AddTransaction.route) {
            AddTransactionScreen(
                null,
                null,
                { navController.navigate(Routes.Home.route) },
                modifier
            )
        }

        composable(
            Routes.AddTransaction.route + "/{accountItem}" + "/{categoryItem}",
            arguments = listOf(
                navArgument("accountItem") {
                    type = NavType.ParcelableType(Expense::class.java)
                },
                navArgument("categoryItem") {
                    type = NavType.ParcelableType(Expense::class.java)
                },
            )
        ) {

                navBackStack ->
            AddTransactionScreen(
                navBackStack.arguments?.getParcelable("accountItem"),
                navBackStack.arguments?.getParcelable("categoryItem"),
                { navController.navigate(Routes.Home.route) }
            )
        }
    }
}
