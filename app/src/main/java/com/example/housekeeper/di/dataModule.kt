package com.example.housekeeper.di

import androidx.room.Room
import com.example.housekeeper.data.bd.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDataBase::class.java, "database.db")
            .build().categoryDao()
    }

    single {
        Room.databaseBuilder(androidContext(), AppDataBase::class.java, "database.db")
            .build().transactionDao()
    }
}