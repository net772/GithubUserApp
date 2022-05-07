package com.example.githubuserapp.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.utility.config.KoinConstants
import com.example.githubuserapp.state.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

abstract class BaseViewModel(
    private val app: Application
): AndroidViewModel(app), KoinComponent {

    private val ioDispatcher: CoroutineDispatcher by inject(named(KoinConstants.DISPATCHER_IO))

    protected fun <T> Flow<T>.onState(collect: (ResultState<T>) -> Unit) {
        flowOn(ioDispatcher)
            .onCompletion { collect(ResultState.Finish) }
            .onStart { collect(ResultState.Loading) }
            .catch { collect(ResultState.Error(it)) }
            .onEach { collect(ResultState.Success(it)) }
            .launchIn(viewModelScope)
    }
}