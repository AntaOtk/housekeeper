package com.example.housekeeper.domain

interface BaseRepository {

    fun checkFirstStart():Boolean

    fun setFirstStartFlag()

}
