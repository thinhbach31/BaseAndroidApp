package com.example.listing.domain.usecase

import com.example.listing.domain.repository.ListingRepository
import javax.inject.Inject

class ObserveListingUseCase @Inject constructor(
    private val repository: ListingRepository,
) {
    operator fun invoke() = repository.observe()
}
