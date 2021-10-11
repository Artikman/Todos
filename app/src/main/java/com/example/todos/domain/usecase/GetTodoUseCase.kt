package com.example.todos.domain.usecase

import com.example.todos.domain.base.BaseUseCase
import com.example.todos.domain.entity.TodoItem
import com.example.todos.domain.repository.TodosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodoUseCase @Inject constructor(
    private val todoRepository: TodosRepository
) : BaseUseCase<Int, Flow<TodoItem>> {

    override suspend fun invoke(params: Int?): Flow<TodoItem> =
        todoRepository.getTodo(id = params!!)
}