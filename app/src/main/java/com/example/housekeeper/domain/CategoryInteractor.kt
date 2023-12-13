package com.example.housekeeper.domain

import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface CategoryInteractor {
    suspend fun setCategory(category: Expense)

    fun getCategories(): Flow<List<Expense>>
}
