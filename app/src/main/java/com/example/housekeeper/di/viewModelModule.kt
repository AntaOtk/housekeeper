package com.example.housekeeper.di

import com.example.housekeeper.presentation.MainViewModel
import com.example.housekeeper.presentation.calculator.ColculaterViewModel
import com.example.housekeeper.presentation.creator.ConstructorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ConstructorViewModel(get()) }
    viewModel { ColculaterViewModel(get()) }
    viewModel { MainViewModel(get()) }
}