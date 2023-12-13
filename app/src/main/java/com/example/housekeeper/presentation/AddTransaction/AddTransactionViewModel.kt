package com.example.housekeeper.presentation.AddTransaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housekeeper.domain.CategoryInteractor
import com.example.housekeeper.domain.TransactionInteractor
import com.example.housekeeper.domain.model.Expense
import com.example.housekeeper.domain.model.Transaction
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class AddTransactionViewModel(
    private val interactor: TransactionInteractor,
    private val categoryInteractor: CategoryInteractor
) : ViewModel() {

    private val categoriesLiveData = MutableLiveData<List<Expense>>()

    fun observeCategoriesLiveData(): LiveData<List<Expense>> = categoriesLiveData
    private var actualSum = ""
    private val toAccountId: Long? = null
    private val fromAccount = MutableLiveData<Expense>()
    fun observeFromAccountId(): LiveData<Expense> = fromAccount


    fun addTransaction() {
        viewModelScope.launch {
            interactor.setTransaction(Transaction(LocalDateTime.now().toString(),actualSum, toAccountId, fromAccount.value?.id))
        }
    }

    fun showAccount() {
        viewModelScope.launch {
            categoryInteractor.getCategories()
                .collect { categories ->
                    categoriesLiveData.postValue(categories)
                }
        }
    }

    fun setSum(s: String) {
        if (s != actualSum) actualSum = s
    }

    fun setCategory(category: Expense) {
        fromAccount.postValue(category)
    }
}