package com.example.todos.di

import com.example.todos.subnavigation.LocalCiceroneHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TodosLocalNavigationModule {

    @Singleton
    @Provides
    fun provideLocalNavigationHolder(): LocalCiceroneHolder = LocalCiceroneHolder()
}