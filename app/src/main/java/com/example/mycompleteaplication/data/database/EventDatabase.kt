package com.example.mycompleteaplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycompleteaplication.data.database.dao.EventDao
import com.example.mycompleteaplication.data.database.entities.EventEntity



@Database(entities = [EventEntity::class], version = 1)
abstract class EventDatabase : RoomDatabase(){

    abstract  fun getEventDao():EventDao
}