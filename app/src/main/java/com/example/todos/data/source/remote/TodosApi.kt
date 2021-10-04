package com.example.todos.data.source.remote

import com.example.todos.domain.entity.Todos

interface TodosApi {
    suspend fun getAndPrintTodos(): Todos
}