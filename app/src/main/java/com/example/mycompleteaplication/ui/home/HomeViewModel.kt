package com.example.mycompleteaplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycompleteaplication.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val preferences: Repository): ViewModel(){

    suspend fun getDarkThemeValue() : Boolean{
        var dark: Boolean
        return viewModelScope.async {
            preferences.getDarkThemeValue() == true
        }.await()
    }


}