package com.example.housekeeper.domain.model

import java.sql.Timestamp

data class Transaction(
    val date: Timestamp,
    val sum: String,
    val toId:Long?,
    val fromId: Long?,
)
