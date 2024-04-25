package com.example.mycompleteaplication.data.preferences

interface Preferences {
    suspend fun putDarkThemeValue(value: Boolean)

    suspend fun getDarkThemeValue(): Boolean?
}