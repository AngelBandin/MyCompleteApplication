package com.example.mycompleteaplication.di

import android.content.Context
import com.example.mycompleteaplication.data.preferences.Preferences
import com.example.mycompleteaplication.data.preferences.PreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {
    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext app: Context): Preferences = PreferencesImpl(app)

}