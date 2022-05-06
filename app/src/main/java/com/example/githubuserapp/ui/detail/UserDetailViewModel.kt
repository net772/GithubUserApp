package com.example.githubuserapp.ui.detail

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.data.response.GithubData
import com.example.githubuserapp.state.ResultState
import com.example.githubuserapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserDetailViewModel(
    app: Application,
    private val githubData: GithubData
) : BaseViewModel(app) {
    private val _userDetailStateLiveData = MutableStateFlow<ResultState<GithubData>>(ResultState.UnInitialize)
    val userDetailStateLiveData = _userDetailStateLiveData.asStateFlow()

    override fun fetchData() = viewModelScope.launch {
        _userDetailStateLiveData.value = ResultState.Loading
    }
}