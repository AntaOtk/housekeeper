package com.example.housekeeper.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("category_table")
data class CategoryEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val iconSRC: Int
)
