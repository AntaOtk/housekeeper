package com.example.housekeeper.domain

import com.example.housekeeper.costomView.model.Item
import com.example.housekeeper.domain.model.StatisticDate
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate


interface StatisticInteractor {
    suspend fun getStatistic(period: List<LocalDate>): List<StatisticDate>
}
