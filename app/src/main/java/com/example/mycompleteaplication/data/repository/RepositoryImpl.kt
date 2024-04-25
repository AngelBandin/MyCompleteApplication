package com.example.mycompleteaplication.data.repository

import com.example.mycompleteaplication.data.preferences.Preferences
import com.example.mycompleteaplication.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val preferences : Preferences): Repository {
    override suspend fun putDarkThemeValue(value: Boolean) {
        preferences.putDarkThemeValue(value)
    }

    override suspend fun getDarkThemeValue(): Boolean? {
        return preferences.getDarkThemeValue()
    }


}