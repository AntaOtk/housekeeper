package com.example.housekeeper.domain.Impl

import com.example.housekeeper.domain.AccountRepository
import com.example.housekeeper.domain.BaseInteractor
import com.example.housekeeper.domain.BaseRepository
import com.example.housekeeper.domain.CategoryRepository
import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.flow.Flow

class BaseInteractorImpl(
    private val baseRepository: BaseRepository,
    private val categoryRepository: CategoryRepository,
    private val accountRepository: AccountRepository
) : BaseInteractor {
    override fun getCategories(): Flow<List<Expense>> {
        return categoryRepository.getCategories()
    }

    override fun getAccounts(): Flow<List<Expense>> {
        return accountRepository.getAccounts()
    }

    override suspend fun checkFirstStart() {
        if (baseRepository.checkFirstStart()) {
            accountRepository.setBaseAccounts()
            categoryRepository.setBaseCategories()
            baseRepository.setFirstStartFlag()
        }
    }
}