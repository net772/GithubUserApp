package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.repository.DbRepository
import com.example.githubuserapp.domain.UseCase

class ReqDeleteUserLikedUseCase(
    private val dbRepository: DbRepository
) : UseCase {
    suspend operator fun invoke(id: Long) = dbRepository.deleteUserLiked(id)
}