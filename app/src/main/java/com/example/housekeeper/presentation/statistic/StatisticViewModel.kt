package com.example.housekeeper.presentation.statistic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housekeeper.costomView.model.Item
import com.example.housekeeper.domain.StatisticInteractor
import com.example.housekeeper.domain.model.StatisticDate
import kotlinx.coroutines.launch
import java.time.LocalDate

class StatisticViewModel(private val interactor: StatisticInteractor) : ViewModel() {

    private val period = mutableListOf<LocalDate>()
    private val statisticLiveData = MutableLiveData<List<Item>>()
    fun observeStatisticLiveData(): LiveData<List<Item>> = statisticLiveData


    init {
        val year = LocalDate.now().year
        val month = LocalDate.now().month
        period.add(LocalDate.of(year, month, 1))
    }

    fun getStatistic() {
        viewModelScope.launch {
            interactor.getStatistic(period)
        }
    }

    fun setStatisticLiveData(data: List<StatisticDate>) {

    }
}