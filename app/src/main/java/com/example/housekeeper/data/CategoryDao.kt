package com.example.housekeeper.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {
    @Insert
    fun insertCategory(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category_table")
    fun getCategories(): List<CategoryEntity>
}