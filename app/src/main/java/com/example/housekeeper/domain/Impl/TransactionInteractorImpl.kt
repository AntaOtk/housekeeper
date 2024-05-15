package com.example.housekeeper.domain.Impl

import com.example.housekeeper.domain.AccountRepository
import com.example.housekeeper.domain.CategoryRepository
import com.example.housekeeper.domain.TransactionInteractor
import com.example.housekeeper.domain.TransactionRepository
import com.example.housekeeper.domain.model.Expense
import com.example.housekeeper.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

class TransactionInteractorImpl(
    private val repository: TransactionRepository,
    private val categoryRepository: CategoryRepository,
    private val accountRepository: AccountRepository): TransactionInteractor {

    override suspend fun setTransaction(transaction: Transaction){
        repository.setTransaction(transaction)
    }

    override fun getCategory(id: Long): Flow<Expense> {
        return categoryRepository.getCategory(id)
    }

    override fun getAccount(id: Long): Flow<Expense> {
        return accountRepository.getAccount(id)
    }
}