package com.example.core.common.di

import com.example.core.common.dispatcher.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @Provides
    @Singleton
    fun provideDispatchers(): AppDispatchers = AppDispatchers(
        main = Dispatchers.Main,
        io = Dispatchers.IO,
        default = Dispatchers.Default,
    )
}
