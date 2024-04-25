package com.example.mycompleteaplication.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mycompleteaplication.domain.model.Event

@Entity(tableName = "event_table")
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "horaInicio") val horaInicio: String,
    @ColumnInfo(name = "horaFin") val horaFin: String
)

fun Event.toDatabase(fecha: Long) = EventEntity(date = fecha,nombre = nombre, horaInicio = horaInicio, horaFin = horaFin, id = 0)