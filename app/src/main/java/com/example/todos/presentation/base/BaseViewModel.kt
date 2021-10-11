package com.example.todos.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<VS : ViewState?, A : Action> : ViewModel(), CoroutineScope {

    protected abstract fun initialViewState(): VS

    private val _eventChannel = Channel<A>(Channel.BUFFERED)
    internal val actionFlow: Flow<A> get() = _eventChannel.receiveAsFlow()

    private val _viewStateFlow: MutableStateFlow<VS> = MutableStateFlow(initialViewState())
    val viewStateFlow: StateFlow<VS> = _viewStateFlow

    internal fun sendAction(action: A) {
        launch {
            _eventChannel.send(action)
        }
    }

    protected fun reduceViewState(reducer: (VS) -> VS) {
        launch {
            _viewStateFlow.emit(reducer(viewStateFlow.value))
        }
    }

    private val defaultCoroutineExceptionHandler = CoroutineExceptionHandler { _, _ -> }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob() + defaultCoroutineExceptionHandler

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }
}