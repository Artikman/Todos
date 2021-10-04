package com.example.todos.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todos.domain.repository.TodosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TodosViewModel @Inject constructor(private val todosRepository: TodosRepository) :
    ViewModel() {

    fun getTodos() {
        viewModelScope.launch {
            todosRepository.getAllTodos()
        }
    }
}