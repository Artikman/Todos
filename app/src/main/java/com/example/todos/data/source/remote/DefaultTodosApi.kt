package com.example.todos.data.source.remote

import com.example.todos.domain.entity.Todos
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultTodosApi @Inject constructor(
    private val httpClient: HttpClient
) : TodosApi {

    override suspend fun getAndPrintTodos(): Todos {
        val todos: Todos = httpClient.get(TODOS_URL)
        httpClient.close()
        return todos
    }

    companion object {
        private const val TODOS_URL = "https://gorest.co.in/public-api/todos"
    }
}