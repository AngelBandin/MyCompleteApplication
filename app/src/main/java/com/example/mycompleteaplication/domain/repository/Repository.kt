package com.example.mycompleteaplication.domain.repository

import dagger.Provides


interface Repository {
    suspend fun putDarkThemeValue(value: Boolean)

    suspend fun getDarkThemeValue(): Boolean?
}