package com.example.todos.presentation.todosdetails

import com.example.todos.domain.entity.TodoItem
import com.example.todos.presentation.base.Action
import com.example.todos.presentation.base.ViewState

data class TodosDetailsViewState(
    val todoItem: TodoItem? = null
) : ViewState

sealed class TodoDetailsAction : Action