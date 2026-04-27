package com.example.baseandroidapp.data.users.remote

import com.example.baseandroidapp.domain.users.User

/**
 * Network DTO for the users endpoint. Stays at the data-layer boundary —
 * map to the domain model with [toDomain] before crossing layers.
 *
 * NOTE: parsed reflectively via Moshi's [KotlinJsonAdapterFactory] (registered
 * in NetworkModule) instead of `@JsonClass(generateAdapter = true)` because
 * moshi-kotlin-codegen currently breaks under KSP + Kotlin 2.2 with
 * "unexpected jvm signature V". For high-throughput projects, switch back to
 * codegen once that's fixed upstream.
 */
data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
) {
    fun toDomain(): User = User(id = id, name = name, email = email)
}
