package com.example.housekeeper.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housekeeper.domain.BaseInteractor
import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.launch

class MainViewModel(val interactor: BaseInteractor) : ViewModel() {
    private val accountLiveData = MutableLiveData<List<Expense>>()
    fun observeAccountLiveData(): LiveData<List<Expense>> = accountLiveData
    private val categoryLiveData = MutableLiveData<List<Expense>>()
    fun observeCategoryLiveData(): LiveData<List<Expense>> = categoryLiveData

    init {
        checkFirstStart()
    }

    fun getCategories() {
        viewModelScope.launch {
            interactor.getCategories().collect() {
                categoryLiveData.postValue(it)
            }
        }
    }

    fun getAccounts() {
        viewModelScope.launch {
            interactor.getAccounts().collect() {
                accountLiveData.postValue(it)
            }
        }
    }

    private fun checkFirstStart() {
        viewModelScope.launch {
            interactor.checkFirstStart()
            getAccounts()
            getCategories()
        }
    }
}