package com.example.housekeeper.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [TransactionEntity::class, CategoryEntity::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao

}