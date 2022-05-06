package com.example.githubuserapp.ui.main

import android.app.Application
import com.example.githubuserapp.ui.base.BaseViewModel
import kotlinx.coroutines.Job

class MainViewModel(
    app: Application
): BaseViewModel(app) {
    override fun fetchData(): Job = Job()

}