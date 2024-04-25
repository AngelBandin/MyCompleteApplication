package com.example.mycompleteaplication.data.repository

import com.example.mycompleteaplication.data.database.dao.EventDao
import com.example.mycompleteaplication.data.database.entities.EventEntity
import com.example.mycompleteaplication.domain.model.Event
import javax.inject.Inject


class EventRepository @Inject constructor(
    private val eventDao: EventDao) {

    suspend fun getEventsFromApi(): List<Event> {
        return emptyList()
    }
    suspend fun getEventsFromDatabase(): List<EventEntity> {
        return eventDao.getAllEvents()
    }

    suspend fun insertEvents(events : List<EventEntity>){
        println(events)
        eventDao.insertAllEvents(events)
    }

    suspend fun clearEvents() {
        eventDao.deleteAllEvents()
    }
    suspend fun clearAndInsertEvents(events : List<EventEntity>){
        eventDao.deleteAndInsertAllEvents(events)
    }
}