package com.example.housekeeper.domain

import com.example.housekeeper.domain.model.Expense

interface CategoryInteractor {
    suspend fun setCategory(category: Expense)
}
