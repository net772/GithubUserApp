package com.example.githubuserapp.di

import com.example.githubuserapp.data.repository.ApiRepository
import com.example.githubuserapp.data.repository.ApiRepositoryImpl
import com.example.githubuserapp.data.repository.DbRepository
import com.example.githubuserapp.data.repository.DbRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ApiRepository> {  ApiRepositoryImpl(get() ) }
    single<DbRepository> {  DbRepositoryImpl(get() ) }
}