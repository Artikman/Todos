package com.example.todos

import android.os.Bundle
import android.transition.ChangeBounds
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.todos.presentation.todosdetails.TodosDetailsFragment
import com.example.todos.presentation.todoslist.TodosFragment
import com.example.todos.subnavigation.Screens.todosScreen
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(todosScreen())))
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    private val navigator: Navigator = object : AppNavigator(this, R.id.container_main) {
        override fun setupFragmentTransaction(
            screen: FragmentScreen,
            fragmentTransaction: FragmentTransaction,
            currentFragment: Fragment?,
            nextFragment: Fragment
        ) {
            if (currentFragment is TodosFragment
                && nextFragment is TodosDetailsFragment
            ) {
                setupSharedElementForTodosToTodosDetails(
                    currentFragment,
                    nextFragment,
                    fragmentTransaction
                )
            }
        }
    }

    private fun setupSharedElementForTodosToTodosDetails(
        todosFragment: TodosFragment,
        todosDetailsFragment: TodosDetailsFragment,
        fragmentTransaction: FragmentTransaction
    ) {
        val changeBounds = ChangeBounds()
        todosDetailsFragment.sharedElementEnterTransition = changeBounds
        todosDetailsFragment.sharedElementReturnTransition = changeBounds
        todosFragment.sharedElementEnterTransition = changeBounds
        todosFragment.sharedElementReturnTransition = changeBounds
    }

    override fun onBackPressed() {
        router.exit()
    }
}