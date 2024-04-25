package com.example.mycompleteaplication.di

import android.content.Context
import androidx.room.Room
import com.example.mycompleteaplication.data.database.EventDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    const val EVENT_DATABASE_NAME = "event_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context)
    = Room.databaseBuilder(context, EventDatabase::class.java, EVENT_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideEventDao(db: EventDatabase) = db.getEventDao()
}