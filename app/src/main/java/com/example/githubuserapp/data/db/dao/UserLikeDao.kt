package com.example.githubuserapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubuserapp.data.entity.UserLikeEntity

@Dao
interface UserLikeDao {

    @Query("SELECT * FROM UserLikeEntity")
    suspend fun getAll(): List<UserLikeEntity>

    @Query("SELECT * FROM UserLikeEntity WHERE id=:id")
    suspend fun get(id: Long): UserLikeEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userLikeEntity: UserLikeEntity)

    @Query("DELETE FROM UserLikeEntity WHERE id=:id")
    suspend fun delete(id: Long)
}