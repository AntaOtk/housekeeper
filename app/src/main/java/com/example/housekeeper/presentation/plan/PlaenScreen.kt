package com.example.housekeeper.presentation.plan

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.housekeeper.compose.StatisticCart
import com.example.housekeeper.domain.model.Expense

@Composable
fun PlanScreen() {
    val categoryList = remember { mutableStateOf(listOf<Expense>()) }
    Column {
        for (item in categoryList.value) {
            StatisticCart(item.name, item.sum ?: 0.0, item.planingSum ?: 0.0)
        }
    }
}