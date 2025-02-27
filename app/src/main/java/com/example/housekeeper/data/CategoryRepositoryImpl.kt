package com.example.housekeeper.data

import com.example.housekeeper.data.bd.CategoryDao
import com.example.housekeeper.data.bd.CategoryEntity
import com.example.housekeeper.data.bd.TransactionDao
import com.example.housekeeper.domain.CategoryRepository
import com.example.housekeeper.domain.model.Expense
import com.example.housekeeper.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryRepositoryImpl(
    private val dao: CategoryDao,
    private val transactionDao: TransactionDao
) :
    CategoryRepository {
    override suspend fun setCategory(category: Expense) {
        dao.insertCategory(mapToEntity(category))
    }

    override suspend fun getAllCategories(): List<Expense> {
        val categories = dao.getCategories()
        return (categories.map { category -> mapFromEntity(category) })
    }

    override fun getCategory(id: Long): Flow<Expense> = flow {
        emit(mapFromEntity(dao.getCategory(id)))
    }

    override suspend fun getCategories(): List<Expense> {
        val categories = mutableListOf<Expense>()
        val allCategories = dao.getCategories()
        for (category in allCategories) {
            val item = mapFromEntity(category)
            if (item.sum > 0) categories.add(item)
        }
        return categories
    }


    override suspend fun setBaseCategories() {
        for (item in Constants.firstCategoryList) {
            dao.insertCategory(item)
        }
    }

    private suspend fun mapFromEntity(categoryEntity: CategoryEntity): Expense {
        return Expense(
            categoryEntity.id,
            categoryEntity.categoryName,
            categoryEntity.id?.let { getSum(it) } ?: 0.0,
            categoryEntity.limit,
            categoryEntity.iconSRC,
        )
    }

    private fun mapToEntity(category: Expense): CategoryEntity {
        return CategoryEntity(
            category.id,
            category.name,
            category.image,
            category.planingSum
        )
    }

    private suspend fun getSum(id: Long): Double {
        var sum = 0.0
        val transactions = transactionDao.getTransactionWithCategory(id)
        for (item in transactions) {
            sum += item.sum
        }
        return sum
    }
}