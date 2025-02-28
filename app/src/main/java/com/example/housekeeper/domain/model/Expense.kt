package com.example.housekeeper.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Expense(
    val id: Long?,
    val name: String,
    val sum: Double,
    val planingSum: Double?,
    val image: Int,
) : Parcelable
