package com.example.housekeeper.data.bd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("account_table")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String,
    val iconSRC: Int,
)
