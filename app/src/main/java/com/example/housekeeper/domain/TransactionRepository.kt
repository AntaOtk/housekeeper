package com.example.housekeeper.domain

import com.example.housekeeper.domain.model.Transaction
import java.time.LocalDate

interface TransactionRepository {
    suspend fun setTransaction(transaction: Transaction)
    suspend fun getStatisticOfPeriod(period: List<LocalDate>, categoryID: Long): Long

}
