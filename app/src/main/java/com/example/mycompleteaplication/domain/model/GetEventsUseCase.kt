package com.example.mycompleteaplication.domain.model


import com.example.mycompleteaplication.data.repository.EventRepository
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(private val repository: EventRepository) {
    suspend operator fun invoke(): Map<Long, List<Event>> {
        val events = repository.getEventsFromDatabase()
        println(events)
        val aux = events.groupBy { it.date }.mapValues { (_, events) -> events.map { eventEntity -> eventEntity.toDomain()  } }
        return aux
    }
}