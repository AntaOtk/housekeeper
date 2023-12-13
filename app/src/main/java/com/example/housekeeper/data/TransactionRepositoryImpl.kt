package com.example.housekeeper.data

import com.example.housekeeper.data.bd.TransactionDao
import com.example.housekeeper.data.bd.TransactionEntity
import com.example.housekeeper.domain.TransactionRepository
import com.example.housekeeper.domain.model.Transaction

class TransactionRepositoryImpl(val dao: TransactionDao): TransactionRepository {
    override suspend fun setTransaction(transaction: Transaction) {
        val transactionEntity = mapTransactionToEntity(transaction)
        dao.insertTransaction(transactionEntity)
    }

    private fun mapTransactionToEntity(transaction: Transaction): TransactionEntity{
        return TransactionEntity(
            null,
            transaction.date,
            transaction.sum.toDouble(),
            transaction.toId,
            transaction.fromId
        )
    }
}