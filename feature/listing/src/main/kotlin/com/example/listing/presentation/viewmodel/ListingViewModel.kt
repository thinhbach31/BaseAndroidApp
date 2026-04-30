package com.example.listing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listing.domain.usecase.ObserveListingUseCase
import com.example.listing.domain.usecase.RefreshListingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(
    observeListing: ObserveListingUseCase,
    private val refreshListing: RefreshListingUseCase,
) : ViewModel() {

    val state = observeListing()
        .map { ListingUiState(items = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
            initialValue = ListingUiState(),
        )

    fun onEvent(event: ListingUiEvent) {
        viewModelScope.launch {
            when (event) {
                ListingUiEvent.Refresh -> refreshListing()
            }
        }
    }

    private companion object {
        const val STOP_TIMEOUT_MS = 5_000L
    }
}
