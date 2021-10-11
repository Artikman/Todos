package com.example.todos.presentation.todosdetails

import com.example.todos.domain.usecase.GetTodoUseCase
import com.example.todos.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosDetailsViewModel @Inject constructor(
    private val getTodoUseCase: GetTodoUseCase
) : BaseViewModel<TodosDetailsViewState, TodoDetailsAction>() {

    override fun initialViewState(): TodosDetailsViewState = TodosDetailsViewState()

    fun getTodosDetails(id: Int) {
        launch {
            getTodoUseCase.invoke(id)
                .collect { items ->
                    reduceViewState {
                        it.copy(
                            todoItem = items
                        )
                    }
                }
        }
    }
}