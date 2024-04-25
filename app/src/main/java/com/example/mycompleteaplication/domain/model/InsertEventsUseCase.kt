package com.example.mycompleteaplication.domain.model

import com.example.mycompleteaplication.data.database.entities.toDatabase
import com.example.mycompleteaplication.data.repository.EventRepository
import javax.inject.Inject

class InsertEventsUseCase @Inject constructor(private val repository: EventRepository) {
    suspend operator fun invoke(events :Map<Long,List<Event>>) {
        val aux = events.flatMap { (date,events) -> events.map { event -> event.toDatabase(date) } }
        println(aux)
        repository.clearAndInsertEvents(aux)
    }
}