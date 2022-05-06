package com.example.githubuserapp.ui.users

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.data.response.GithubData
import com.example.githubuserapp.domain.usecase.GetGithubUseCase
import com.example.githubuserapp.state.ResultState
import com.example.githubuserapp.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersViewModel (
    app: Application,
    private val getGithubUseCase: GetGithubUseCase
) : BaseViewModel(app) {

    private val _userState = MutableStateFlow<ResultState<List<GithubData>>>(ResultState.UnInitialize)
    val userState = _userState.asStateFlow()

    override fun fetchData(): Job = viewModelScope.launch {
        getGithubUseCase.invoke().onState {
            Log.d("동현"," : $it")
            _userState.value = it
        }
    }
}