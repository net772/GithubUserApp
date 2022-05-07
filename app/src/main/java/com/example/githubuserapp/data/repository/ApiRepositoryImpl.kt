package com.example.githubuserapp.data.repository

import com.example.githubuserapp.data.response.GithubData
import com.example.githubuserapp.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class  ApiRepositoryImpl(
    private val service: ApiService
) : ApiRepository {

    override fun getGitHub(): Flow<List<GithubData>> = flow {
        emit(service.getGithub())
    }.map { it.items }

}