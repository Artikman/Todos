package com.example.todos.domain.entity

import androidx.room.Embedded

data class Meta(
    @Embedded
    val pagination: Pagination
)