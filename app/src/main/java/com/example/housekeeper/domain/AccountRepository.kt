package com.example.housekeeper.domain

import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun setAccount(account: Expense)
    suspend fun setBaseAccounts()
    suspend fun getAccounts(): List<Expense>
    suspend fun getAccount(id: Long): Expense
}