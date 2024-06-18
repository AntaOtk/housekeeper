package com.example.housekeeper.data

import com.example.housekeeper.data.bd.TransactionDao
import com.example.housekeeper.data.bd.TransactionEntity
import com.example.housekeeper.domain.TransactionRepository
import com.example.housekeeper.domain.model.Transaction
import java.sql.Timestamp
import java.time.LocalDate

class TransactionRepositoryImpl(private val dao: TransactionDao) : TransactionRepository {
    override suspend fun setTransaction(transaction: Transaction) {
        val transactionEntity = mapTransactionToEntity(transaction)
        dao.insertTransaction(transactionEntity)
    }

    override suspend fun getStatisticOfPeriod(period: List<LocalDate>, categoryID: Long): Long {
      return dao.getTransactionSum(categoryID, Timestamp.valueOf(period[0].toString()) , Timestamp.valueOf(period[1].toString()))
    }

    private fun mapTransactionToEntity(transaction: Transaction): TransactionEntity {
        return TransactionEntity(
            null,
            transaction.date,
            transaction.sum.toDouble(),
            transaction.toId,
            transaction.fromId
        )
    }
}