package com.example.listing.data.di

import com.example.listing.data.repository.ListingRepositoryImpl
import com.example.listing.domain.repository.ListingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// ListingDao + ListingApi are provided by :app's AppDataModule.
@Module
@InstallIn(SingletonComponent::class)
abstract class ListingDataModule {
    @Binds
    @Singleton
    abstract fun bindRepository(impl: ListingRepositoryImpl): ListingRepository
}
