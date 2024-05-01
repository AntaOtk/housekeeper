package com.example.housekeeper.domain

import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun setAccount(account: Expense)
    suspend fun setBaseAccounts()
    fun getAccounts(): Flow<List<Expense>>
    fun getAccount(id: Long): Flow<Expense>
}