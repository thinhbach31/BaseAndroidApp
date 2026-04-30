package com.example.listing.presentation.viewmodel

import com.example.listing.domain.model.ListingItem

data class ListingUiState(
    val items: List<ListingItem> = emptyList(),
    val isRefreshing: Boolean = false,
    val error: String? = null,
)
