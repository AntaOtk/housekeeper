package com.example.housekeeper.di

import android.content.Context
import androidx.room.Room
import com.example.housekeeper.data.bd.AppDataBase
import com.example.housekeeper.data.local.LocalStorage
import com.example.housekeeper.data.local.SharedPreferenceClient
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

    single {
        Room.databaseBuilder(androidContext(), AppDataBase::class.java, "database.db")
            .build().accountDao()
    }

    single {
        androidContext()
            .getSharedPreferences("local_storage", Context.MODE_PRIVATE)
    }

    single<LocalStorage> { SharedPreferenceClient(get()) }
}