package com.example.githubuserapp.data.network

import com.example.githubuserapp.data.response.GithubResponse
import retrofit2.http.GET

interface ApiService {

    @GET("search/users?q=shop")
    suspend fun getGithub(): GithubResponse

}