package com.example.todos.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todos.data.source.local.dao.TodosDao
import com.example.todos.domain.entity.DataConverters
import com.example.todos.domain.entity.Todos

@Database(entities = [Todos::class], version = 1, exportSchema = false)
@TypeConverters(DataConverters::class)
abstract class TodosDatabase : RoomDatabase() {
    abstract fun getTodosDao(): TodosDao
}