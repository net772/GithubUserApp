package com.example.githubuserapp.di

import com.example.githubuserapp.domain.usecase.GetGithubUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetGithubUseCase(get()) }
}