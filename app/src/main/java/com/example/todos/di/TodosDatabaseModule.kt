package com.example.todos.di

import android.content.Context
import androidx.room.Room
import com.example.todos.data.source.local.TodosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class TodosDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            TodosDatabase::class.java,
            "todos_database"
        ).build()

    @Singleton
    @Provides
    fun provideTodosDao(database: TodosDatabase) = database.getTodosDao()
}