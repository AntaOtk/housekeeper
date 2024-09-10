package com.example.housekeeper.di

import com.example.housekeeper.domain.BaseInteractor
import com.example.housekeeper.domain.CategoryInteractor
import com.example.housekeeper.domain.impl.BaseInteractorImpl
import com.example.housekeeper.domain.impl.CategoryInteractorImpl
import com.example.housekeeper.domain.impl.TransactionInteractorImpl
import com.example.housekeeper.domain.impl.StatisticInteractorImpl
import com.example.housekeeper.domain.TransactionInteractor
import com.example.housekeeper.domain.StatisticInteractor
import org.koin.dsl.module

val interactorModule = module {
    single<CategoryInteractor>{ CategoryInteractorImpl(get(),get()) }
    single<TransactionInteractor> { TransactionInteractorImpl(get(),get(),get()) }
    single<BaseInteractor> { BaseInteractorImpl(get(),get(),get()) }
    single<StatisticInteractor> { StatisticInteractorImpl(get(),get()) }

}