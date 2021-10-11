package com.example.todos.presentation.todoslist

import com.example.todos.domain.entity.TodoItem
import com.example.todos.presentation.base.Action
import com.example.todos.presentation.base.ViewState

data class TodosListViewState(
    val todosList: List<TodoItem> = listOf()
) : ViewState

sealed class TodoListAction : Action {
    data class ShowTodoDetailsScreen(val id: Int) : TodoListAction()
}