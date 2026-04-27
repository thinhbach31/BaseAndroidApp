package com.example.baseandroidapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baseandroidapp.data.users.local.UserDao
import com.example.baseandroidapp.data.users.local.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
