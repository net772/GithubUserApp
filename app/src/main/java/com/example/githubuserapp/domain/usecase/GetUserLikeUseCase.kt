package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.repository.DbRepository
import com.example.githubuserapp.domain.UseCase

class GetUserLikeUseCase(
    private val dbRepository: DbRepository
) : UseCase {
    operator fun invoke(id: Long) = dbRepository.getUserLike(id)
}