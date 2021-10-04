package com.example.todos.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todos.domain.entity.Todos

@Dao
interface TodosDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTodos(todos: Todos)

    @Query("SELECT * FROM todos")
    fun getAllTodos(): Todos?
}