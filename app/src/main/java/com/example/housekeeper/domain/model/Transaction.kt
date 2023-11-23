package com.example.housekeeper.domain.model

data class Transaction(
    val date: String,
    val sum: String,
    val toId:String,
    val fromId: String,
)
