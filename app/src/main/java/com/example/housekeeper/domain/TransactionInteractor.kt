package com.example.housekeeper.domain

import com.example.housekeeper.domain.model.Transaction

interface TransactionInteractor {
    suspend fun setTransaction(trensaction: Transaction)
}