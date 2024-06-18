package com.example.housekeeper.data.bd

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "transaction_table")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val date: Timestamp,
    val sum: Double,
    val fromId: Long?,
    val toId: Long?,
)