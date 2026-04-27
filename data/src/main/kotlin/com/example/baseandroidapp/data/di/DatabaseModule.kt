package com.example.baseandroidapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.baseandroidapp.data.local.AppDatabase
import com.example.baseandroidapp.data.users.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "base-android-app.db").build()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()
}
