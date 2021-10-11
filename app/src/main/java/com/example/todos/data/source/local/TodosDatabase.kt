package com.example.todos.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todos.data.source.local.converters.TodoListConverter
import com.example.todos.data.source.local.dao.TodoEntity
import com.example.todos.data.source.local.dao.TodosDao

@Database(
    entities = [
        TodoEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    value = [
        TodoListConverter::class
    ]
)
abstract class TodosDatabase : RoomDatabase() {

    abstract fun getTodosDao(): TodosDao
}