package com.example.housekeeper.domain.Impl

import com.example.housekeeper.domain.AccountRepository
import com.example.housekeeper.domain.StatisticInteractor
import com.example.housekeeper.domain.TransactionRepository
import com.example.housekeeper.domain.model.StatisticDate
import java.time.LocalDate

class StatisticInteractorImpl(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository
) : StatisticInteractor {
    override suspend fun getStatistic(period: List<LocalDate>): List<StatisticDate> {
        val accountSum =
            accountRepository.getAccounts().map { account -> StatisticDate(0, account) }
        for (account in accountSum) {
            account.value =
                account.category.id?.let { transactionRepository.getStatisticOfPeriod(period, it) }
                    ?: 0
        }
        return accountSum
    }
}