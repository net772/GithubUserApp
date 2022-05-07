package com.example.githubuserapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubuserapp.data.db.dao.UserLikeDao
import com.example.githubuserapp.data.entity.UserLikeEntity

@Database(
    entities = [UserLikeEntity::class],
    version = 1,
    exportSchema = false
)

abstract class ApplicationDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "ApplicationDataBase.db"
    }

    abstract fun UserLikeDao() : UserLikeDao
}