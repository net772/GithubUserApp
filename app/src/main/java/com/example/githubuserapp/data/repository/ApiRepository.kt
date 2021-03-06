package com.example.githubuserapp.data.repository

import com.example.githubuserapp.data.response.GithubData
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    fun getGitHub(): Flow<List<GithubData>>
}