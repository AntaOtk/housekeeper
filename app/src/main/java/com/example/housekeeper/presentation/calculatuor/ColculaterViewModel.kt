package com.example.housekeeper.presentation.calculatuor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housekeeper.domain.TransactionInteractor
import com.example.housekeeper.domain.model.Transaction
import kotlinx.coroutines.launch

class ColculaterViewModel(val interactor: TransactionInteractor) : ViewModel() {

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            interactor.setTransaction(transaction)
        }
    }
}