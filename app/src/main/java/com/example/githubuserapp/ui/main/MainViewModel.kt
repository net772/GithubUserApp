package com.example.githubuserapp.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.data.entity.UserLikeEntity
import com.example.githubuserapp.domain.usecase.GetGithubUseCase
import com.example.githubuserapp.domain.usecase.GetUserAllLikeUseCase
import com.example.githubuserapp.domain.usecase.GetUserLikeUseCase
import com.example.githubuserapp.state.ResultState
import com.example.githubuserapp.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(
    app: Application,
    private val getGithubUseCase: GetGithubUseCase,
    private val getUserAllLikeUseCase: GetUserAllLikeUseCase,
    private val getUserLikeUseCase: GetUserLikeUseCase,
) : BaseViewModel(app) {

    companion object {
        const val TAB_ALL = 0
        const val TAB_LIKE = 1
    }

    private val _userList = MutableLiveData<ArrayList<UserLikeEntity>>()
    val userList: MutableLiveData<ArrayList<UserLikeEntity>> get() = _userList

    private val _userState = MutableStateFlow<ResultState<List<UserLikeEntity>>>(ResultState.UnInitialize)
    val userState = _userState.asStateFlow()

    private val _userLikeResponseState = MutableStateFlow<ResultState<List<UserLikeEntity>>>(
        ResultState.UnInitialize)
    val userLikeResponseState = _userLikeResponseState.asStateFlow()

    override fun fetchData(): Job = viewModelScope.launch {
        getGithubUseCase.invoke()
            .map { it.map { githubData ->
                var userLikeEntity = githubData.toEntity()
                val userLikeEntityData = getUserLikeUseCase.invoke(userLikeEntity.id).firstOrNull()

                if (userLikeEntityData != null) {
                    userLikeEntity = userLikeEntity.copy(state = true)
                }
                userLikeEntity
            } }
            .onState {
                _userState.value = it
            }
    }

    fun getUserLikesData() = viewModelScope.launch {
        getUserAllLikeUseCase.invoke().onState {
            _userLikeResponseState.value = it
        }
    }

    fun setUserList(list: ArrayList<UserLikeEntity>) {
        _userList.value = list
    }
}