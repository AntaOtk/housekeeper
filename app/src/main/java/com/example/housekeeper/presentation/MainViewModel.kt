package com.example.housekeeper.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housekeeper.domain.BaseInteractor
import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.launch

class MainViewModel(val interactor: BaseInteractor) : ViewModel() {
    private val categoryLiveData = MutableLiveData<List<Expense>>()
    fun observeCategoryLiveData(): LiveData<List<Expense>> = categoryLiveData
    init {
        getCategories()
    }
    fun getCategories() {
        viewModelScope.launch {
            interactor.getCategories().collect() {
                categoryLiveData.postValue(it)
            }
        }
    }
}