package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.repository.ApiRepository
import com.example.githubuserapp.domain.UseCase

class GetGithubUseCase(
    private val apiRepository: ApiRepository
) : UseCase {
    operator fun invoke() = apiRepository.getGitHub()
}