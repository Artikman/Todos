package com.example.todos.domain.usecase

import com.example.todos.domain.base.BaseUseCase
import com.example.todos.domain.entity.TodoItem
import com.example.todos.domain.repository.TodosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(
    private val todoRepository: TodosRepository
) : BaseUseCase<Unit, Flow<List<TodoItem>>> {

    override suspend fun invoke(params: Unit?): Flow<List<TodoItem>> =
        todoRepository.getTodos()
}