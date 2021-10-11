package com.example.todos.presentation.todosdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.todos.R
import com.example.todos.databinding.FragmentTodosDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TodosDetailsFragment : Fragment(R.layout.fragment_todos_details) {

    private val viewModel by viewModels<TodosDetailsViewModel>()
    private val binding: FragmentTodosDetailsBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()
        val id = arguments?.getInt(DETAILS_ID)
        if (id != null) {
            viewModel.getTodosDetails(id)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewStateFlow.collect(::setViewState)
        }
    }

    private fun setViewState(state: TodosDetailsViewState) {
        state.todoItem?.let { todoItem ->
            with(binding) {
                tvTitle.text = todoItem.title
                tvDueOn.text = todoItem.dueOn
            }
        }
    }

    companion object {

        private const val DETAILS_ID = "details_id"

        fun getNewInstance(id: Int): TodosDetailsFragment {
            return TodosDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(DETAILS_ID, id)
                }
            }
        }
    }
}