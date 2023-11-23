package com.example.housekeeper.data.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDao {
    @Insert(entity = TransactionEntity::class)
    fun insertTransaction(transactionEntity: TransactionEntity)

    @Query("SELECT * FROM transaction_table WHERE toId = :categoryId")
    fun getTransactionWithCategory(categoryId: String): List<TransactionEntity>
}