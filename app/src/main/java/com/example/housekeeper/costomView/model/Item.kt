package com.example.housekeeper.costomView.model

import com.example.housekeeper.domain.model.Expense

data class Item(
    val value: Long,
    val category: Expense
)
