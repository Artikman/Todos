package com.example.todos.domain.usecase

import com.example.todos.domain.base.BaseUseCase
import com.example.todos.domain.repository.TodosRepository
import javax.inject.Inject

class UpdateTodosUseCase @Inject constructor(
    private val todoRepository: TodosRepository
) : BaseUseCase<Unit, Unit> {

    override suspend fun invoke(params: Unit?): Unit =
        todoRepository.updateTodosList()
}