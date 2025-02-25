package com.example.housekeeper.data

import com.example.housekeeper.data.local.LocalStorage
import com.example.housekeeper.domain.BaseRepository

class BaseRepositoryImpl(private val local: LocalStorage) : BaseRepository {

    override fun checkFirstStart():Boolean{
        return local.checkFirstStart()
    }

    override fun setFirstStartFlag(){
        local.setFirstStartFlag()
    }
}
