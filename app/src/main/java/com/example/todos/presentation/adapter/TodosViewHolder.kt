package com.example.todos.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.todos.databinding.ItemTodosBinding
import com.example.todos.domain.entity.Todos

class TodosViewHolder(private val binding: ItemTodosBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(todos: Todos) {
        with(binding) {
            todosTitle.text = todos.data.map { it.title }.toString()
        }
    }
}