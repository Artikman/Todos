package com.example.todos.data.source.remote

import com.example.todos.data.source.remote.response.TodoResponse
import com.example.todos.domain.core.result.Result

interface TodosApi {

    suspend fun getTodos(): Result<TodoResponse>
}