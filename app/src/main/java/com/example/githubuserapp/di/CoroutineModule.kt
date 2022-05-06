package com.example.githubuserapp.di

import com.example.baseproject.utility.config.KoinConstants
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coroutineModule = module {

    single(named(KoinConstants.DISPATCHER_DEFAULT)) {
        Dispatchers.Default
    }
    single(named(KoinConstants.DISPATCHER_IO)) {
        Dispatchers.IO
    }
}