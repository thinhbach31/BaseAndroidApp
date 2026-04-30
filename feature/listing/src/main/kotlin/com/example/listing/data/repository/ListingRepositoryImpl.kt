package com.example.listing.data.repository

import com.example.listing.data.local.ListingDao
import com.example.listing.data.local.toDomain
import com.example.listing.data.local.toEntity
import com.example.listing.data.remote.ListingApi
import com.example.listing.data.remote.toDomain
import com.example.listing.domain.model.ListingItem
import com.example.listing.domain.repository.ListingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val api: ListingApi,
    private val dao: ListingDao,
) : ListingRepository {

    override fun observe(): Flow<List<ListingItem>> =
        dao.observeAll().map { rows -> rows.map { it.toDomain() } }

    override suspend fun refresh() {
        val items = api.fetch().map { it.toDomain() }
        dao.upsertAll(items.map { it.toEntity() })
    }
}
