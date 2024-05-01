package com.example.housekeeper.data.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AccountDao {
    @Insert(AccountEntity::class)
    suspend fun insertAccount(accountEntity: AccountEntity)
    @Query("SELECT * FROM account_table")
    suspend fun getAccounts(): List<AccountEntity>
    @Query("SELECT * FROM account_table WHERE id = :selectId")
    suspend fun getAccount(selectId: Long): AccountEntity
}