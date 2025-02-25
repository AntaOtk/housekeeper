package com.example.housekeeper.data.bd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("category_table")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val categoryName: String,
    val iconSRC: Int,
    val limit: Double?
)
