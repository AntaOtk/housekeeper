package com.example.housekeeper.domain

import com.example.housekeeper.domain.model.Transaction

interface TransactionRepository {
    suspend fun setTransaction(transaction: Transaction)

}
