package com.example.housekeeper.costomView.model

import androidx.annotation.IntRange

data class Item(
    val value: Int,
    val category: Int
)

val mockData: List<Item>
    get() = List(5) {
        Item((2..13).random(), it)
    }