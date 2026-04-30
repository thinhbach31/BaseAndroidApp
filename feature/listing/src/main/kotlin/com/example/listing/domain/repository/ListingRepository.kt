package com.example.listing.domain.repository

import com.example.listing.domain.model.ListingItem
import kotlinx.coroutines.flow.Flow

interface ListingRepository {
    fun observe(): Flow<List<ListingItem>>
    suspend fun refresh()
}
