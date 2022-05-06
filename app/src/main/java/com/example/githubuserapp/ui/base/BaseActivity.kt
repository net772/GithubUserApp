package com.example.githubuserapp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job

abstract class BaseActivity<VM: BaseViewModel, VB: ViewBinding>: AppCompatActivity() {
    abstract val viewModel: VM

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract fun getViewBinding(): VB
    protected open fun initActivity() = Unit

    private lateinit var fetchJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)

        initActivity()
        fetchJob = viewModel.fetchData()
    }

    override fun onDestroy() {
        if (fetchJob.isActive) {
            fetchJob.cancel()
        }
        _binding = null
        super.onDestroy()
    }
}