package com.example.githubuserapp.data.repository

import com.example.githubuserapp.data.response.GithubData
import com.example.githubuserapp.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val service: ApiService
) : Repository {

    override fun getGitHub(): Flow<List<GithubData>> = flow {
        emit(service.getGithub())
    }.map { it.items }

}