package com.example.housekeeper.data

import com.example.housekeeper.R
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

    override suspend fun setBaseCategories() {
        val list = listOf(
            CategoryEntity(null,
                "home",
                R.drawable.home,
            ),
            CategoryEntity(null,
                "transport",
                R.drawable.car_servise,
            ),
            CategoryEntity(null,
                "product",
                R.drawable.cosmetic,
            ),
            CategoryEntity(null,
                "restaurant",
                R.drawable.vaccines,
            ),
            CategoryEntity(null,
                "education",
                R.drawable.vaccines,
            ),
            CategoryEntity(null,
                "clothes",
                R.drawable.clothes,
            ),
            CategoryEntity(null,
                "pet",
                R.drawable.cosmetic,
            ),
        )
        for (item in list){ dao.insertCategory(item)}
    }

    private fun mapFromEntity(categoryEntity: CategoryEntity): Expense {
        return Expense(
            categoryEntity.id,
            categoryEntity.name,
            null,
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
}