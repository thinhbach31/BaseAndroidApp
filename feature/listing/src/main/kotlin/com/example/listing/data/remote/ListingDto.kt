package com.example.listing.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ListingDto(
    val id: String,
    val title: String,
    val subtitle: String,
    val imageUrl: String? = null,
)
