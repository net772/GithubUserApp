package com.example.githubuserapp.di

import com.example.githubuserapp.data.network.buildOkHttpClient
import com.example.githubuserapp.data.network.provideGithupApiService
import com.example.githubuserapp.data.network.provideGithupRetrofit
import com.example.githubuserapp.data.network.provideGsonConverterFactory
import org.koin.dsl.module

val networkModule = module {
    single { provideGsonConverterFactory() }

    single { buildOkHttpClient() }

    single { provideGithupRetrofit(get(), get()) }

    single { provideGithupApiService(get()) }
}
