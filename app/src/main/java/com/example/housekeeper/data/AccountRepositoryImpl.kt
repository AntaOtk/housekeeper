package com.example.housekeeper.data

import android.util.Log
import com.example.housekeeper.R
import com.example.housekeeper.data.bd.AccountDao
import com.example.housekeeper.data.bd.AccountEntity
import com.example.housekeeper.data.bd.TransactionDao
import com.example.housekeeper.domain.AccountRepository
import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AccountRepositoryImpl(
    private val dao: AccountDao,
    private val transactionDao: TransactionDao
) : AccountRepository {
    override suspend fun setAccount(account: Expense) {
        dao.insertAccount(mapToEntity(account))
    }

    override fun getAccounts(): Flow<List<Expense>> = flow {
        val accounts = dao.getAccounts()
        emit(accounts.map { account -> mapFromEntity(account) })
    }

    override fun getAccount(id: Long): Flow<Expense> = flow {
        emit(mapFromEntity(dao.getAccount(id)))
    }

    override suspend fun setBaseAccounts() {
        val list = listOf(
            AccountEntity(
                1,
                "1",
                R.drawable.euro,
            ),
            AccountEntity(
                2,
                "2",
                R.drawable.euro,
            )
        )
        for (item in list) {
            dao.insertAccount(item)
            Log.d("DBaccount", "account $item")
        }
    }

    private suspend fun mapFromEntity(accountEntity: AccountEntity): Expense {
        return Expense(
            accountEntity.id,
            accountEntity.name,
            accountEntity.id?.let { getSum(it) },
            accountEntity.iconSRC,
        )
    }

    private fun mapToEntity(expense: Expense): AccountEntity {
        return AccountEntity(
            expense.id,
            expense.name,
            expense.image,
        )
    }

    private suspend fun getSum(id: Long): Double {
        var sum = 0.0
        val transactions = transactionDao.getTransactionWithCategory(id)
        for (item in transactions) {
            sum += item.sum
        }
        return sum
    }
}