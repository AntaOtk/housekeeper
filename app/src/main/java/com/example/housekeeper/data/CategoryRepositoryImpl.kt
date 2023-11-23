package com.example.housekeeper.data

import com.example.housekeeper.data.bd.CategoryDao
import com.example.housekeeper.data.bd.CategoryEntity
import com.example.housekeeper.domain.CategoryRepository
import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryRepositoryImpl(val dao: CategoryDao) : CategoryRepository {
    override suspend fun setCategory(category: Expense) {
        dao.insertCategory(mapToEntity(category))
    }

    override fun getCategories(): Flow<List<Expense>> = flow {
        val categories = dao.getCategories()
        emit(categories.map { category -> mapFromEntity(category) })
    }

    private fun mapFromEntity(categoryEntity: CategoryEntity): Expense {
        return Expense(
            categoryEntity.id,
            categoryEntity.name,
            null,
            categoryEntity.iconSRC,
            categoryEntity.color
        )
    }

    private fun mapToEntity(category: Expense): CategoryEntity {
        return CategoryEntity(
            category.id,
            category.name,
            category.image,
            category.color
        )
    }
}