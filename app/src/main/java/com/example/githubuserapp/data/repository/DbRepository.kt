package com.example.githubuserapp.data.repository

import com.example.githubuserapp.data.entity.UserLikeEntity
import com.example.githubuserapp.data.response.GithubData
import kotlinx.coroutines.flow.Flow

interface DbRepository {
    fun getUserAllLike(): Flow<List<UserLikeEntity>>
    fun getUserLike(id: Long): Flow<UserLikeEntity>
    suspend fun insertUserLiked(userLikeEntity: UserLikeEntity)
    suspend fun deleteUserLiked(id: Long)
}