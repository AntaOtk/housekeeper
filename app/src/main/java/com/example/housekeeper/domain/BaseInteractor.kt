package com.example.housekeeper.domain

import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface BaseInteractor {
    fun getCategories(): Flow<List<Expense>>

     fun getAccounts(): Flow<List<Expense>>
    suspend fun checkFirstStart()
}