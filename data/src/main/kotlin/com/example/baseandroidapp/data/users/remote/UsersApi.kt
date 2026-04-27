package com.example.baseandroidapp.data.users.remote

import retrofit2.http.GET

interface UsersApi {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
}
