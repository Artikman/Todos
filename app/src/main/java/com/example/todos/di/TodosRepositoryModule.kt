package com.example.todos.di

import com.example.todos.domain.repository.TodosRepository
import com.example.todos.data.repository.DefaultTodosRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class TodosRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindTodosRepository(repository: DefaultTodosRepository): TodosRepository
}