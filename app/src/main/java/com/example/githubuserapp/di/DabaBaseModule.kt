package com.example.githubuserapp.di

import com.example.githubuserapp.data.db.provideDB
import com.example.githubuserapp.data.db.provideUserLikeDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataBaseModule = module {
    single { provideDB(androidApplication()) }
    single { provideUserLikeDao(get()) }
}
