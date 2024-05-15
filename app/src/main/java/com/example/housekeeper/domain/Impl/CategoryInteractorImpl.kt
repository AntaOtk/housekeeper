package com.example.housekeeper.domain.Impl

import com.example.housekeeper.domain.AccountRepository
import com.example.housekeeper.domain.CategoryInteractor
import com.example.housekeeper.domain.CategoryRepository
import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.flow.Flow

class CategoryInteractorImpl(private val categoryRepository: CategoryRepository, private val accountRepository: AccountRepository ) : CategoryInteractor {
    override suspend fun setCategory(category: Expense) {
        categoryRepository.setCategory(category)
    }

    override fun getCategories(): Flow<List<Expense>> {
        return categoryRepository.getCategories()
    }

    override fun getAccounts(): Flow<List<Expense>> {
        return accountRepository.getAccounts()
    }
}
