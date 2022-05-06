package com.example.githubuserapp.di

import com.example.githubuserapp.data.repository.Repository
import com.example.githubuserapp.data.repository.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> {  RepositoryImpl(get() ) }
}