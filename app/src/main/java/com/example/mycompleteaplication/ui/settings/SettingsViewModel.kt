package com.example.mycompleteaplication.ui.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycompleteaplication.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.prefs.Preferences
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(private val preferences: Repository): ViewModel(){
    suspend fun putDarkThemeValue(value: Boolean) {
        viewModelScope.launch {
            preferences.putDarkThemeValue(value)
        }
    }

    suspend fun getDarkThemeValue() : Boolean{
        var dark: Boolean
        return viewModelScope.async {
            preferences.getDarkThemeValue() == true
        }.await()
    }


}