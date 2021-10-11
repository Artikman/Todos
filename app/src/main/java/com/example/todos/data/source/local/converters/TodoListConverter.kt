package com.example.todos.data.source.local.converters

import androidx.room.TypeConverter
import com.example.todos.data.fromJson
import com.example.todos.data.source.local.dao.TodoEntity
import com.google.gson.Gson

class TodoListConverter {
    private val gson = Gson()

    @TypeConverter
    fun toColumn(list: List<TodoEntity>): String = gson.toJson(list)

    @TypeConverter
    fun toList(column: String): List<TodoEntity> = gson.fromJson(column)!!
}