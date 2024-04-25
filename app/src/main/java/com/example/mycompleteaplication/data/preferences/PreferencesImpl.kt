package com.example.mycompleteaplication.data.preferences

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private const val PREFERENCES_NAME = "settings"
private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)
private val KEY_DARKMODE = booleanPreferencesKey("key_darkmode")

class PreferencesImpl @Inject constructor(private val context: Context): Preferences {

    override suspend fun putDarkThemeValue(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[KEY_DARKMODE] = value
        }
    }

    override suspend fun getDarkThemeValue(): Boolean? {
        return try {
            val preferences = context.dataStore.data.first()
            preferences[KEY_DARKMODE]
        }
        catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}
