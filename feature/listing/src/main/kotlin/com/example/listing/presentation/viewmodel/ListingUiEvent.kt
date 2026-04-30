package com.example.listing.presentation.viewmodel

sealed interface ListingUiEvent {
    data object Refresh : ListingUiEvent
}
