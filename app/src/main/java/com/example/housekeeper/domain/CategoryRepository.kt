package com.example.housekeeper.domain

import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun setCategory(category: Expense)
    suspend fun getCategories(): List<Expense>
    suspend fun setBaseCategories()
    fun getCategory(id: Long): Flow<Expense>
}
