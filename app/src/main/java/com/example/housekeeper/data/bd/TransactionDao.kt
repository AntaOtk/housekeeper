package com.example.housekeeper.data.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.sql.Timestamp

@Dao
interface TransactionDao {
    @Insert(entity = TransactionEntity::class)
    suspend fun insertTransaction(transactionEntity: TransactionEntity)

    @Query("SELECT * FROM transaction_table WHERE toId = :categoryId")
    suspend fun getTransactionWithCategory(categoryId: Long): List<TransactionEntity>

    @Query("SELECT SUM sum FROM transaction_table WHERE date BETWEEN :startData AND :endData ")
    suspend fun getTransactionSum(categoryId: Long, startData: Timestamp, endData:Timestamp): Long
}