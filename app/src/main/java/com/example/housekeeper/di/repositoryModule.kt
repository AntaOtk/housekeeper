package com.example.housekeeper.di

import com.example.housekeeper.data.CategoryRepositoryImpl
import com.example.housekeeper.data.TransactionRepositoryImpl
import com.example.housekeeper.domain.BaseRepository
import com.example.housekeeper.domain.BaseRepositoryImpl
import com.example.housekeeper.domain.CategoryRepository
import com.example.housekeeper.domain.TransactionRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get(),get()) }
    single<BaseRepository> { BaseRepositoryImpl(get()) }
    single<TransactionRepository> { TransactionRepositoryImpl(get()) }
}