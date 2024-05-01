package com.example.housekeeper.domain.Impl

import com.example.housekeeper.domain.CategoryInteractor
import com.example.housekeeper.domain.CategoryRepository
import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.flow.Flow

class CategoryInteractorImpl(val repository: CategoryRepository) : CategoryInteractor {
    override suspend fun setCategory(category: Expense) {
        repository.setCategory(category)
    }

    override fun getCategories(): Flow<List<Expense>> {
        return repository.getCategories()
    }

    override fun getAccounts(): Flow<List<Expense>> {
        return repository.getAccounts()
    }

    override fun getCategory(id: Long): Flow<Expense> {
        return repository.getCategory(id)
    }

}
