package com.example.notes.data.remote

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface NoteApi {
    @GET("notes")
    suspend fun list(): List<NoteDto>

    @POST("notes")
    suspend fun create(@Body dto: NoteDto): NoteDto

    @PUT("notes/{id}")
    suspend fun update(@Path("id") id: String, @Body dto: NoteDto): NoteDto

    @DELETE("notes/{id}")
    suspend fun delete(@Path("id") id: String)
}
