package com.example.housekeeper.data.bd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val date: String,
    val sum: Double,
    val toId:Long?,
    val fromId: Long?,
)