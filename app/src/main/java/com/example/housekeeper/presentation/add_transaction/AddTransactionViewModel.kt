package com.example.housekeeper.presentation.add_transaction

import android.util.Log
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

    private val accountsLiveData = MutableLiveData<List<Expense>>()
    fun observeAccountsLiveData(): LiveData<List<Expense>> = accountsLiveData
    private var actualSum = ""
    private val fromAccount = MutableLiveData<Expense>()
    private val toAccount = MutableLiveData<Expense>()
    fun observeToAccount(): LiveData<Expense> = toAccount
    fun observeFromAccount(): LiveData<Expense> = toAccount
    private val enabledState = MutableLiveData(false)
    fun observeEnabledState(): LiveData<Boolean> = enabledState
    fun addTransaction() {
        viewModelScope.launch {
            interactor.setTransaction(
                Transaction(
                    LocalDateTime.now().toString(),
                    actualSum,
                    fromAccount.value?.id,
                    toAccount.value?.id
                )
            )
        }
    }

    fun showCategories() {
        viewModelScope.launch {
            categoryInteractor.getCategories()
                .collect { categories ->
                    categoriesLiveData.postValue(categories)
                }
        }
    }

    fun showAccounts() {
        viewModelScope.launch {
            categoryInteractor.getAccounts()
                .collect { accounts ->
                    accountsLiveData.postValue(accounts)
                }
        }
    }

    fun setSum(s: String) {
        if (s != actualSum) actualSum = s
        checkEnable()
    }

    private fun checkEnable() {
        if ((fromAccount.value != null) || (toAccount.value != null)) enabledState.postValue(
            actualSum.isNotEmpty()
        )
    }

    fun setCategory(category: Expense) {
        toAccount.postValue(category)
        checkEnable()
    }

    fun setAccount(account: Expense) {
        fromAccount.postValue(account)
        checkEnable()
    }

    fun setAccountFromID(id: Long?) {
        if (id != null) {
            viewModelScope.launch {
                interactor.getCategory(id).collect { category ->
                    setCategory(category)
                    Log.d("drag", "category $category")
                }
            }
        }
    }
}