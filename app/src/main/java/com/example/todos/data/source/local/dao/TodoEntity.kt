package com.example.todos.data.source.local.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todos.domain.entity.TodoItem

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val status: String,
    val dueOn: String
)

fun TodoEntity.toTodoItem(): TodoItem {
    return TodoItem(
        id = id,
        title = title,
        status = status,
        dueOn = dueOn
    )
}