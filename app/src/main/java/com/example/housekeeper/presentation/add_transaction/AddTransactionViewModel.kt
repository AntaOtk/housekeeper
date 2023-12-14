package com.example.housekeeper.presentation.add_transaction

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
    private var fromAccountId: Long? = null
    private val toAccount = MutableLiveData<Expense>()
    fun observeToAccountId(): LiveData<Expense> = toAccount
    private val enabledState = MutableLiveData(false)
    fun observeEnabledState(): LiveData<Boolean> = enabledState
    fun addTransaction() {
        viewModelScope.launch {
            interactor.setTransaction(Transaction(LocalDateTime.now().toString(),actualSum, fromAccountId, toAccount.value?.id))
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
        checkEnable()
    }

    private fun checkEnable(){
        if ((fromAccountId != null) || (toAccount.value != null))enabledState.postValue(actualSum.isNotEmpty())
    }

    fun setCategory(category: Expense) {
        toAccount.postValue(category)
        checkEnable()
    }
}