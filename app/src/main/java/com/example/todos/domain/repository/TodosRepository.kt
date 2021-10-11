package com.example.todos.domain.repository

import com.example.todos.domain.entity.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodosRepository {

    suspend fun getTodo(id: Int): Flow<TodoItem>
    suspend fun getTodos(): Flow<List<TodoItem>>

    suspend fun updateTodosList()
}