package com.example.housekeeper.domain.impl

import com.example.housekeeper.costomView.model.Item
import com.example.housekeeper.domain.AccountRepository
import com.example.housekeeper.domain.StatisticInteractor
import com.example.housekeeper.domain.TransactionRepository
import com.example.housekeeper.domain.model.StatisticDate
import java.time.LocalDate

class StatisticInteractorImpl(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository
) : StatisticInteractor {
    private var accountSum = mutableListOf<StatisticDate>()

    override suspend fun getStatistic(period: List<LocalDate>): List<Item> {
        accountRepository.getAccounts().collect { list ->
            accountSum.addAll(list.map { account -> StatisticDate(0.0, account) })
        }
        for (account in accountSum) {
            account.value =
                (account.category.id?.let { transactionRepository.getStatisticOfPeriod(period, it) }
                    ?: 0) as Double
        }
        return accountSum.map { item -> mapToItem(item) }
    }

    private fun mapToItem(date: StatisticDate): Item {
        return Item(date.value, date.category)
    }
}