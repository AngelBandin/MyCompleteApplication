package com.example.mycompleteaplication.ui.calendar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycompleteaplication.domain.model.Event
import com.example.mycompleteaplication.domain.model.GetEventsUseCase
import com.example.mycompleteaplication.domain.model.InsertEventsUseCase
import com.example.mycompleteaplication.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val insertEventsUseCase: InsertEventsUseCase
):ViewModel() {
    var isLoading = MutableStateFlow<Boolean>(true)
    lateinit var eventosPorDia: Map<Long, List<Event>>
    init{
        viewModelScope.launch {
            val events = getEventsUseCase()
            eventosPorDia = events.ifEmpty { mapOf() }
            println(eventosPorDia)
            isLoading.value = false

        }
    }

    suspend fun insertEvents(events : Map<Long, List<Event>>){
            eventosPorDia = events
            insertEventsUseCase(events)
    }

}