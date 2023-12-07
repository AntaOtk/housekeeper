package com.example.housekeeper.di

import com.example.housekeeper.domain.BaseInteractor
import com.example.housekeeper.domain.CategoryInteractor
import com.example.housekeeper.domain.Impl.BaseInteractorImpl
import com.example.housekeeper.domain.Impl.CategoryInteractorImpl
import com.example.housekeeper.domain.Impl.TransactionInteractorImpl
import com.example.housekeeper.domain.TransactionInteractor
import org.koin.dsl.module

val interactorModule = module {
    single<CategoryInteractor>{ CategoryInteractorImpl(get()) }
    single<TransactionInteractor> { TransactionInteractorImpl() }
    single<BaseInteractor> { BaseInteractorImpl(get(),get()) }

}