package com.example.mycompleteaplication.domain.model

import com.example.mycompleteaplication.data.database.entities.EventEntity
import com.example.mycompleteaplication.data.model.EventModel

data class Event (
    val nombre: String,
    val horaInicio: String,
    val horaFin: String,
    var checked: Boolean = false
)

fun EventModel.toDomain() = Event(nombre,horaInicio,horaFin)
fun EventEntity.toDomain() = Event(nombre,horaInicio,horaFin)