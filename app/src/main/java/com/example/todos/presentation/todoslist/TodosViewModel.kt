package com.example.todos.presentation.todoslist

import com.example.todos.domain.usecase.GetTodosUseCase
import com.example.todos.domain.usecase.UpdateTodosUseCase
import com.example.todos.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val updateTodosUseCase: UpdateTodosUseCase
) : BaseViewModel<TodosListViewState, TodoListAction>() {

    override fun initialViewState() = TodosListViewState()

    init {
        launch {
            getTodosUseCase()
                .collect { items ->
                    reduceViewState {
                        it.copy(
                            todosList = items
                        )
                    }
                }
        }

        launch {
            updateTodosUseCase()
        }
    }

    fun onItemClicked(id: Int) {
        sendAction(TodoListAction.ShowTodoDetailsScreen(id))
    }
}