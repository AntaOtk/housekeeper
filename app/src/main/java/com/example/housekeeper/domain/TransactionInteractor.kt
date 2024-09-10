package com.example.housekeeper.domain

import com.example.housekeeper.domain.model.Expense
import com.example.housekeeper.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionInteractor {
    suspend fun setTransaction(transaction: Transaction)
    fun getCategory(id: Long): Flow<Expense>
    fun getAccount(id: Long): Flow<Expense>
}