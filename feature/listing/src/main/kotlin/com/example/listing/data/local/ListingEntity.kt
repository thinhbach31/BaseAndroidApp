package com.example.listing.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listings")
data class ListingEntity(
    @PrimaryKey val id: String,
    val title: String,
    val subtitle: String,
    val imageUrl: String?,
)
