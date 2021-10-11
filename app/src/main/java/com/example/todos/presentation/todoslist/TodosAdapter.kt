package com.example.todos.presentation.todoslist

import com.example.todos.databinding.ItemTodosBinding
import com.example.todos.domain.entity.TodoItem
import com.example.todos.utils.itemCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun todoItemAdapterDelegate(
    onItemClicked: (id: Int) -> Unit
) =
    adapterDelegateViewBinding<TodoItem, TodoItem, ItemTodosBinding>(
        { layoutInflater, parent -> ItemTodosBinding.inflate(layoutInflater, parent, false) }
    ) {
        binding.root.setOnClickListener {
            onItemClicked(item.id)
        }

        bind {
            with(binding) {
                tvTitle.text = item.title
            }
        }
    }

internal val todoItemDiffCallback = itemCallback<TodoItem>(
    areItemsTheSame = { oldItem, newItem ->
        oldItem.id == newItem.id
    },
    areContentsTheSame = { oldItem, newItem ->
        oldItem == newItem
    },
    getChangePayload = { _, newItem ->
        newItem
    }
)

class TodosAdapter(
    onItemClicked: (id: Int) -> Unit,
) : AsyncListDifferDelegationAdapter<TodoItem>(
    todoItemDiffCallback
) {
    init {
        delegatesManager.addDelegate(todoItemAdapterDelegate(onItemClicked))
    }
}