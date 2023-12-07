package com.example.housekeeper.domain

import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface BaseInteractor {
    suspend fun getCategories(): Flow<List<Expense>>
}