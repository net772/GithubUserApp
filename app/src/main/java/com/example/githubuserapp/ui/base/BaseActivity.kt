package com.example.githubuserapp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.githubuserapp.state.ResultState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseActivity<VM: BaseViewModel, VB: ViewBinding>: AppCompatActivity() {
    abstract val viewModel: VM

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract fun getViewBinding(): VB
    abstract fun observeData()

    protected open fun initActivity() = Unit

    private lateinit var fetchJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)

        initActivity()
        observeData()
        fetchJob = viewModel.fetchData()
    }

    override fun onDestroy() {
        if (fetchJob.isActive) {
            fetchJob.cancel()
        }
        _binding = null
        super.onDestroy()
    }

    protected fun <T> LiveData<T>.observe(action: (T) -> Unit) {
        observe(this@BaseActivity) { action.invoke(it) }
    }

    private fun <T> Flow<T>.onResult(action: (T) -> Unit) {
        onEach { action.invoke(it) }.launchIn(lifecycleScope)
    }

    protected fun <T> Flow<ResultState<T>>.onUiState(
        loading: () -> Unit = {},
        success: (T) -> Unit = {},
        error: (Throwable) -> Unit = {},
        finish: () -> Unit = {}
    ) {
        onResult { state ->
            when (state) {
                ResultState.Loading -> loading()
                is ResultState.Success -> success(state.data)
                is ResultState.Error -> error(state.error)
                ResultState.Finish -> finish()
                else -> Unit
            }
        }
    }
}