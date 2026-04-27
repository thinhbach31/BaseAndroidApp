package com.example.baseandroidapp.domain.users

/**
 * Domain entity representing a user. Pure Kotlin — no framework dependencies.
 */
data class User(
    val id: Int,
    val name: String,
    val email: String,
)
