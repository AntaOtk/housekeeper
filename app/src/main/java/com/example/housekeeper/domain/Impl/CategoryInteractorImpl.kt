package com.example.housekeeper.domain.Impl

import com.example.housekeeper.domain.AccountRepository
import com.example.housekeeper.domain.CategoryInteractor
import com.example.housekeeper.domain.CategoryRepository
import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryInteractorImpl(
    private val categoryRepository: CategoryRepository,
    private val accountRepository: AccountRepository
) : CategoryInteractor {
    override suspend fun setCategory(category: Expense) {
        categoryRepository.setCategory(category)
    }

    override fun getCategories(): Flow<List<Expense>> = flow {
        emit(categoryRepository.getAllCategories())
    }

    override fun getAccounts(): Flow<List<Expense>> {
        return accountRepository.getAccounts()
    }

    override fun getCategoriesForStatistic(): Flow<Pair<List<Expense>, Double>> = flow {
        val categories = categoryRepository.getCategories()
        var sum = 0.0
        for (item in categories) {
            sum += item.sum
        }
        emit(Pair(categories, sum))
    }
}
