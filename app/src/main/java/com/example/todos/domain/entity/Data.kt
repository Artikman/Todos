package com.example.todos.domain.entity

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("due_on")
    val dueOn: String,
    @SerializedName("status")
    val status: String
)