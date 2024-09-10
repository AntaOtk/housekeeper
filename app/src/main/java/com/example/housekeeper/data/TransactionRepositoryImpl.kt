package com.example.housekeeper.data

import com.example.housekeeper.data.bd.TransactionDao
import com.example.housekeeper.data.bd.TransactionEntity
import com.example.housekeeper.domain.TransactionRepository
import com.example.housekeeper.domain.model.Transaction
import java.time.LocalDate

class TransactionRepositoryImpl(private val dao: TransactionDao) : TransactionRepository {
    override suspend fun setTransaction(transaction: Transaction) {
        val transactionEntity = mapTransactionToEntity(transaction)
        dao.insertTransaction(transactionEntity)
    }

    override suspend fun getStatisticOfPeriod(period: List<LocalDate>, categoryID: Long): Double {
        return dao.getTransactionSum(categoryID, period[0].toEpochDay(), period[1].toEpochDay())
    }

    private fun mapTransactionToEntity(transaction: Transaction): TransactionEntity {
        return TransactionEntity(
            null,
            transaction.date.toEpochDay(),
            transaction.sum.toDouble(),
            transaction.toId,
            transaction.fromId
        )
    }
}