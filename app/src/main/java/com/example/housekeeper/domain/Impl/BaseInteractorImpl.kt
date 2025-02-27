package com.example.housekeeper.domain.Impl

import com.example.housekeeper.domain.AccountRepository
import com.example.housekeeper.domain.BaseInteractor
import com.example.housekeeper.domain.BaseRepository
import com.example.housekeeper.domain.CategoryRepository

class BaseInteractorImpl(
    private val baseRepository: BaseRepository,
    private val categoryRepository: CategoryRepository,
    private val accountRepository: AccountRepository
) : BaseInteractor {
    override suspend fun checkFirstStart() {
        if (baseRepository.checkFirstStart()) {
            accountRepository.setBaseAccounts()
            categoryRepository.setBaseCategories()
            baseRepository.setFirstStartFlag()
        }
    }
}
