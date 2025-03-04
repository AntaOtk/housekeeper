package com.example.housekeeper.presentation.plan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housekeeper.domain.CategoryInteractor
import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.launch

class PlaningViewModel(private val interactor: CategoryInteractor) : ViewModel() {
    private val _categories = MutableLiveData<List<Expense>>()
    val categories: LiveData<List<Expense>> = _categories

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            try {
                interactor.getCategories().collect() {
                    _categories.postValue(it)
                }
            } catch (e: Exception) {
                Log.e("STAT_PROBLEM", e.toString())
            }
        }
    }
}