package com.example.listing.data.remote

import retrofit2.http.GET

interface ListingApi {
    @GET("listings")
    suspend fun fetch(): List<ListingDto>
}
