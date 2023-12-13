package com.example.housekeeper.di

import com.example.housekeeper.presentation.main.MainViewModel
import com.example.housekeeper.presentation.add_transaction.AddTransactionViewModel
import com.example.housekeeper.presentation.creator.ConstructorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ConstructorViewModel(get()) }
    viewModel { AddTransactionViewModel(get(),get()) }
    viewModel { MainViewModel(get()) }
}