package com.example.housekeeper.domain.Impl

import com.example.housekeeper.domain.TransactionInteractor
import com.example.housekeeper.domain.TransactionRepository
import com.example.housekeeper.domain.model.Transaction

class TransactionInteractorImpl(private val repository: TransactionRepository): TransactionInteractor {

    override suspend fun setTransaction(transaction: Transaction){
        repository.setTransaction(transaction)
    }

}