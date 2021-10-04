package com.example.todos.data.repository

import com.example.todos.data.source.local.dao.TodosDao
import com.example.todos.data.source.remote.TodosApi
import com.example.todos.domain.entity.Todos
import com.example.todos.domain.repository.TodosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultTodosRepository @Inject constructor(
    private val todosDao: TodosDao,
    private val todosApi: TodosApi
) : TodosRepository {

    override suspend fun getAllTodos(): Todos = withContext(Dispatchers.IO) {
        return@withContext if (todosDao.getAllTodos() == null) {
            val todos = todosApi.getAndPrintTodos()
            todosDao.insertTodos(todos)
            todos
        } else {
            todosDao.getAllTodos() ?: error("Todo can't be null")
        }
    }
}