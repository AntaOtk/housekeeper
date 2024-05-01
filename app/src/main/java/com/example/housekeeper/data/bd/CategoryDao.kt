package com.example.housekeeper.data.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {
    @Insert(CategoryEntity::class)
    suspend fun insertCategory(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category_table")
    suspend fun getCategories(): List<CategoryEntity>
    @Query("SELECT * FROM category_table WHERE id = :selectId")
    suspend fun getCategory(selectId: Long): CategoryEntity
}