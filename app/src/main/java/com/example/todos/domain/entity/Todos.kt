package com.example.todos.domain.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "todos")
data class Todos(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("code")
    val code: Int,
    @Embedded
    val meta: Meta,
    @TypeConverters(DataConverters::class)
    val data: List<Data>
)