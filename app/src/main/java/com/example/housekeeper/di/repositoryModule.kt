package com.example.housekeeper.di

import com.example.housekeeper.data.CategoryRepositoryImpl
import com.example.housekeeper.domain.CategoryRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get())  }
}