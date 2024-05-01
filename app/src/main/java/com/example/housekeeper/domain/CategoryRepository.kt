package com.example.housekeeper.domain

import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun setCategory(category: Expense)
    fun getCategories(): Flow<List<Expense>>
    suspend fun setBaseCategories()
    fun getAccounts(): Flow<List<Expense>>
    fun getCategory(id: Long): Flow<Expense>

}
