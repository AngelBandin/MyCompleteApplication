package com.example.mycompleteaplication.data.model

import com.example.mycompleteaplication.data.database.entities.EventEntity
import com.example.mycompleteaplication.domain.model.Event

data class EventModel (
    val nombre: String,
    val horaInicio: String,
    val horaFin: String
)

fun Event.toModel() = EventModel(nombre = nombre, horaInicio = horaInicio, horaFin = horaFin)
