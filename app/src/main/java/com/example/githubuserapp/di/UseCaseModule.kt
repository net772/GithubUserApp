package com.example.githubuserapp.di

import com.example.githubuserapp.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetGithubUseCase(get()) }
    factory { GetUserAllLikeUseCase(get()) }
    factory { ReqInsertUserLikeUseCase(get()) }
    factory { ReqDeleteUserLikedUseCase(get()) }
    factory { GetUserLikeUseCase(get()) }
}