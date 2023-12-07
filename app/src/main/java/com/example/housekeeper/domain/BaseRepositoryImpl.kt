package com.example.housekeeper.domain

import com.example.housekeeper.data.local.LocalStorage

class BaseRepositoryImpl(private val local: LocalStorage) : BaseRepository{

    override fun checkFirstStart():Boolean{
        return local.checkFirstStart()
    }

    override fun setFirstStartFlag(){
        local.setFirstStartFlag()
    }
}
