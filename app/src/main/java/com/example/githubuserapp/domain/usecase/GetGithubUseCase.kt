package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.repository.Repository
import com.example.githubuserapp.domain.UseCase

class GetGithubUseCase(
    private val productRepository: Repository
) : UseCase {
    operator fun invoke() = productRepository.getGitHub()
}