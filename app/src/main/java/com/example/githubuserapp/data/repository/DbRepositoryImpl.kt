package com.example.githubuserapp.data.repository

import android.util.Log
import com.example.githubuserapp.data.db.dao.UserLikeDao
import com.example.githubuserapp.data.entity.UserLikeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DbRepositoryImpl(
    private val userLikeDao: UserLikeDao
) : DbRepository {

    override fun getUserAllLike(): Flow<List<UserLikeEntity>> = flow {
        emit( userLikeDao.getAll())
    }.map {
        Log.d("동현","getUserAllLike : $it")
        it }


    override fun getUserLike(id: Long): Flow<UserLikeEntity> {
        return  flow {
            emit(userLikeDao.get(id))
        }.map {
            it
        }

    }
    override suspend fun insertUserLiked(userLikeEntity: UserLikeEntity) {
        userLikeDao.insert(userLikeEntity)
    }

    override suspend fun deleteUserLiked(id: Long) {
        userLikeDao.delete(id)
    }

}