package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.entity.UserLikeEntity
import com.example.githubuserapp.data.repository.DbRepository
import com.example.githubuserapp.domain.UseCase

class ReqInsertUserLikeUseCase(
    private val dbRepository: DbRepository
) : UseCase {
    suspend operator fun invoke(userLikeEntity: UserLikeEntity) = dbRepository.insertUserLiked(userLikeEntity)
}
