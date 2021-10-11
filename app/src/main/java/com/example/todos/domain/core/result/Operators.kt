package com.example.todos.domain.core.result

@Suppress("ExpressionBodySyntax")
fun <T> Result<T>.isSuccess(): Boolean {
    return this is Result.Success
}

@Suppress("ExpressionBodySyntax")
fun <T> Result<T>.asSuccess(): Result.Success<T> {
    return this as Result.Success<T>
}