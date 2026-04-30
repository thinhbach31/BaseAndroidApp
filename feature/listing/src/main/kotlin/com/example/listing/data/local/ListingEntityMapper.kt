package com.example.listing.data.local

import com.example.listing.domain.model.ListingItem

internal fun ListingEntity.toDomain() = ListingItem(
    id = id,
    title = title,
    subtitle = subtitle,
    imageUrl = imageUrl,
)

internal fun ListingItem.toEntity() = ListingEntity(
    id = id,
    title = title,
    subtitle = subtitle,
    imageUrl = imageUrl,
)
