package com.example.todos.domain.entity

data class TodoItem(
    val id: Int,
    val title: String,
    val dueOn: String,
    val status: String
)