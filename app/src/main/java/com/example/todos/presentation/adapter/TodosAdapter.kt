package com.example.todos.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.todos.databinding.ItemTodosBinding
import com.example.todos.domain.entity.Todos

class TodosAdapter : ListAdapter<Todos, TodosViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TodosViewHolder(
        ItemTodosBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Todos>() {
            override fun areItemsTheSame(oldItem: Todos, newItem: Todos): Boolean =
                oldItem.code == newItem.code

            override fun areContentsTheSame(oldItem: Todos, newItem: Todos): Boolean =
                oldItem == newItem
        }
    }
}