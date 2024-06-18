package com.example.housekeeper.presentation.statistic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housekeeper.costomView.model.Item
import com.example.housekeeper.domain.StatisticInteractor
import kotlinx.coroutines.launch
import java.time.LocalDate

class StatisticViewModel(private val interactor: StatisticInteractor) : ViewModel() {

    private val period = mutableListOf<LocalDate>()

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
}