package com.example.housekeeper.domain.model

import java.time.LocalDate


data class Transaction(
    val date: LocalDate,
    val sum: String,
    val toId:Long?,
    val fromId: Long?,
)
