package com.example.listing.data.remote

import com.example.listing.domain.model.ListingItem

internal fun ListingDto.toDomain() = ListingItem(
    id = id,
    title = title,
    subtitle = subtitle,
    imageUrl = imageUrl,
)
