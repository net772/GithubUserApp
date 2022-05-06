package com.example.githubuserapp.di

import com.example.githubuserapp.data.response.GithubData
import com.example.githubuserapp.ui.detail.UserDetailViewModel
import com.example.githubuserapp.ui.main.MainViewModel
import com.example.githubuserapp.ui.users.UsersViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(androidApplication()) }
    viewModel { UsersViewModel(androidApplication(), get()) }
    viewModel { (githubData: GithubData) -> UserDetailViewModel(androidApplication(), githubData) }
}
