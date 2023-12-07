package com.example.housekeeper.data.local

import android.content.SharedPreferences

class SharedPreferenceClient(
    private val sharedPref: SharedPreferences
) : LocalStorage {
    override fun checkFirstStart(): Boolean {
        return sharedPref.getBoolean(FIRST_START, true)
    }

    override fun setFirstStartFlag() {
        sharedPref.edit()
            .putBoolean(FIRST_START, false)
            .apply()
    }

    companion object {
        const val FIRST_START = "isFirstRun"
    }
}