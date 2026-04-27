package com.example.baseandroidapp.core.coroutine

import javax.inject.Qualifier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher

/**
 * Hilt module exposing the standard coroutine dispatchers. Inject these via the
 * qualifier annotations above so tests can substitute deterministic dispatchers.
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides @IoDispatcher fun provideIo(): CoroutineDispatcher = Dispatchers.IO
    @Provides @MainDispatcher fun provideMain(): CoroutineDispatcher = Dispatchers.Main
    @Provides @DefaultDispatcher fun provideDefault(): CoroutineDispatcher = Dispatchers.Default
}
