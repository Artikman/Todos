package com.example.todos.data.source.remote.response

import com.example.todos.data.source.local.dao.TodoEntity
import com.google.gson.annotations.SerializedName

data class TodoResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val todoItems: List<TodoItemResponse>
)

data class TodoItemResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("due_on")
    val dueOn: String?,
    @SerializedName("status")
    val status: String
)

fun TodoItemResponse.toTodoEntity() =
    TodoEntity(
        id = id,
        userId = userId,
        title = title,
        dueOn = dueOn ?: "",
        status = status
    )