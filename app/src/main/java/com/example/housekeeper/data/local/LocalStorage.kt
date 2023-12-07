package com.example.housekeeper.data.local

interface LocalStorage {
    fun checkFirstStart(): Boolean

    fun setFirstStartFlag()
}