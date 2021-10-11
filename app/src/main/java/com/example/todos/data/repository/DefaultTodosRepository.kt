package com.example.todos.data.repository

import com.example.todos.data.source.local.dao.TodosDao
import com.example.todos.data.source.local.dao.toTodoItem
import com.example.todos.data.source.remote.TodosApi
import com.example.todos.data.source.remote.response.toTodoEntity
import com.example.todos.domain.core.result.asSuccess
import com.example.todos.domain.core.result.isSuccess
import com.example.todos.domain.entity.TodoItem
import com.example.todos.domain.repository.TodosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultTodosRepository @Inject constructor(
    private val todosApi: TodosApi,
    private val todosDao: TodosDao
) : TodosRepository {

    override suspend fun getTodo(id: Int): Flow<TodoItem> =
        withContext(Dispatchers.IO) {
            return@withContext todosDao.getTodosDetails(id).map { it.toTodoItem() }
        }

    override suspend fun getTodos(): Flow<List<TodoItem>> =
        withContext(Dispatchers.IO) {
            val items = todosDao.getTodos()
            return@withContext items.map { list ->
                list.map { it.toTodoItem() }
            }
        }

    override suspend fun updateTodosList() {
        withContext(Dispatchers.IO) {
            val todosResponse = todosApi.getTodos()
            if (todosResponse.isSuccess()) {
                val todoEntityList =
                    todosResponse.asSuccess().value.todoItems.map { it.toTodoEntity() }
                todosDao.insertAll(
                    todoEntityList
                )
            }
        }
    }
}