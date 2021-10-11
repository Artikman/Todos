package com.example.todos.subnavigation

import com.example.todos.presentation.todosdetails.TodosDetailsFragment
import com.example.todos.presentation.todoslist.TodosFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
object Screens {

    fun todosScreen() = FragmentScreen {
        TodosFragment()
    }

    fun todosDetailsScreen(id: Int) = FragmentScreen {
        TodosDetailsFragment.getNewInstance(id)
    }
}