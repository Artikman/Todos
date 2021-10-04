package com.example.todos.domain.repository

import com.example.todos.domain.entity.Todos

interface TodosRepository {
    suspend fun getAllTodos(): Todos
}