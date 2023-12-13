package com.example.housekeeper.domain.Impl

import com.example.housekeeper.domain.BaseInteractor
import com.example.housekeeper.domain.BaseRepository
import com.example.housekeeper.domain.CategoryRepository
import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.flow.Flow

class BaseInteractorImpl(private val baseRepository: BaseRepository, private val repository: CategoryRepository): BaseInteractor {
    override suspend fun getCategories(): Flow<List<Expense>> {
        if (baseRepository.checkFirstStart()){
            baseRepository.setFirstStartFlag()
            repository.setBaseCategories()
        }
        return repository.getCategories()
    }
}