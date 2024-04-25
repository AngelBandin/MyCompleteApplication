package com.example.mycompleteaplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.mycompleteaplication.data.database.entities.EventEntity

@Dao
interface EventDao {
    @Query("SELECT * FROM event_table")
    suspend fun getAllEvents(): List<EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEvents(events : List<EventEntity>)
    @Query("DELETE FROM event_table")
    suspend fun deleteAllEvents()

    @Transaction
    suspend fun deleteAndInsertAllEvents(events : List<EventEntity>){
        deleteAllEvents()
        insertAllEvents(events)
    }
}
