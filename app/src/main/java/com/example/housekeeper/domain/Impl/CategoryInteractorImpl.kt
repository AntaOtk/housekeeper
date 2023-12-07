package com.example.housekeeper.domain.Impl

import com.example.housekeeper.domain.CategoryInteractor
import com.example.housekeeper.domain.CategoryRepository
import com.example.housekeeper.domain.model.Expense

class CategoryInteractorImpl(val repository: CategoryRepository) : CategoryInteractor {
    override suspend fun setCategory(category: Expense) {
        repository.setCategory(category)
    }
}