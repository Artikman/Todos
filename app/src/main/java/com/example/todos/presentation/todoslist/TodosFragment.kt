package com.example.todos.presentation.todoslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.todos.R
import com.example.todos.databinding.FragmentTodosBinding
import com.example.todos.subnavigation.Screens.todosDetailsScreen
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TodosFragment : Fragment(R.layout.fragment_todos) {

    @Inject
    lateinit var router: Router

    private val viewModel by viewModels<TodosViewModel>()
    private val binding: FragmentTodosBinding by viewBinding()

    private val todosAdapter by lazy { TodosAdapter(viewModel::onItemClicked) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewStateFlow.collect(::setViewState)
        }
        lifecycleScope.launchWhenStarted {
            viewModel.actionFlow.collect { action ->
                when (action) {
                    is TodoListAction.ShowTodoDetailsScreen -> {
                        router.navigateTo(todosDetailsScreen(action.id))
                    }
                }
            }
        }
    }

    private fun setViewState(state: TodosListViewState) {
        todosAdapter.items = state.todosList
    }

    private fun initRecycler() {
        binding.rvTodos.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = todosAdapter
        }
    }
}