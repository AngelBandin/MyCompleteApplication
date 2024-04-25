package com.example.mycompleteaplication.di

import com.example.mycompleteaplication.data.repository.RepositoryImpl
import com.example.mycompleteaplication.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Repository {
     @Binds
     abstract fun provideRepository(impl: RepositoryImpl): Repository
}