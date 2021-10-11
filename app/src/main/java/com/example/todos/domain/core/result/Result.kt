package com.example.todos.domain.core.result

sealed class Result<T> {

    sealed class Success<T> : Result<T>() {

        abstract val value: T

        override fun toString() = "Success($value)"
    }
}