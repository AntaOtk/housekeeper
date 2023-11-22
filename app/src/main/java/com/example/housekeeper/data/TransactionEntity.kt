package com.example.housekeeper.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class TransactionEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val date: String,
    val sum: String,
    val toId:String,
    val fromId: String,
)