package com.example.listing.domain.usecase

import com.example.listing.domain.repository.ListingRepository
import javax.inject.Inject

class RefreshListingUseCase @Inject constructor(
    private val repository: ListingRepository,
) {
    suspend operator fun invoke() = repository.refresh()
}
