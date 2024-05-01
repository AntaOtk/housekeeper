package com.example.housekeeper.data

import com.example.housekeeper.R
import com.example.housekeeper.data.bd.CategoryDao
import com.example.housekeeper.data.bd.CategoryEntity
import com.example.housekeeper.data.bd.TransactionDao
import com.example.housekeeper.domain.CategoryRepository
import com.example.housekeeper.domain.model.Expense
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

    override fun getCategories(): Flow<List<Expense>> = flow {
        val categories = dao.getCategories()
        emit(categories.map { category -> mapFromEntity(category) })
    }

    override fun getCategory(id: Long): Flow<Expense> = flow {
        emit(mapFromEntity(dao.getCategory(id)))
    }

    override suspend fun setBaseCategories() {
        val list = listOf(
            CategoryEntity(
                null,
                "home",
                R.drawable.home,
            ),
            CategoryEntity(
                null,
                "transport",
                R.drawable.car_servise,
            ),
            CategoryEntity(
                null,
                "product",
                R.drawable.cosmetic,
            ),
            CategoryEntity(
                null,
                "restaurant",
                R.drawable.vaccines,
            ),
            CategoryEntity(
                null,
                "education",
                R.drawable.vaccines,
            ),
            CategoryEntity(
                null,
                "clothes",
                R.drawable.clothes,
            ),
            CategoryEntity(
                null,
                "pet",
                R.drawable.cosmetic,
            ),
        )
        for (item in list) {
            dao.insertCategory(item)
        }
    }

    private suspend fun mapFromEntity(categoryEntity: CategoryEntity): Expense {
        return Expense(
            categoryEntity.id,
            categoryEntity.name,
            categoryEntity.id?.let { getSum(it) },
            categoryEntity.iconSRC,
        )
    }

    private fun mapToEntity(category: Expense): CategoryEntity {
        return CategoryEntity(
            category.id,
            category.name,
            category.image,
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