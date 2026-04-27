package com.example.baseandroidapp.data.di

import com.example.baseandroidapp.data.users.UsersRepositoryImpl
import com.example.baseandroidapp.domain.users.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UsersDataModule {
    @Binds
    abstract fun bindUsersRepository(impl: UsersRepositoryImpl): UsersRepository
}
