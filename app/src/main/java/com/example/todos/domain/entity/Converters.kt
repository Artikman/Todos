package com.example.todos.domain.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverters {

    @TypeConverter
    fun fromDataJson(stat: List<Data>): String {
        return Gson().toJson(stat)
    }

    @TypeConverter
    fun toDataList(jsonData: String): List<Data> {
        val notesType = object : TypeToken<List<Data>>() {}.type
        return Gson().fromJson(jsonData, notesType)
    }
}