package com.example.housekeeper.presentation.statistic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housekeeper.domain.CategoryInteractor
import com.example.housekeeper.domain.model.Expense
import com.example.housekeeper.presentation.model.ChartModel
import com.example.housekeeper.util.Constants
import kotlinx.coroutines.launch

class StatisticViewModel(private val interactor: CategoryInteractor) : ViewModel() {
    private val _categories = MutableLiveData<List<Expense>>()
    val categories: LiveData<List<Expense>> = _categories
    private val _sectors = MutableLiveData<List<ChartModel>>()
    val sectors: LiveData<List<ChartModel>> = _sectors

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            try {
                interactor.getCategoriesForStatistic().collect() {
                    _categories.postValue(it.first)
                    createGraphDate(it.first, it.second)
                }
            } catch (e: Exception) {
                Log.e("STAT_PROBLEM", e.toString())
            }
        }
    }

    private fun createGraphDate(list: List<Expense>, sum: Double) {
        val dateList = mutableListOf<ChartModel>()
        for (i in list.indices) {
            if (list[i].sum > 0)
                dateList.add(
                    ChartModel(
                        value = (list[i].sum / sum).toFloat(),
                        color = Constants.GRAPH_COLOR[i % 10]
                    )
                )
        }
        _sectors.postValue(dateList)
    }
}