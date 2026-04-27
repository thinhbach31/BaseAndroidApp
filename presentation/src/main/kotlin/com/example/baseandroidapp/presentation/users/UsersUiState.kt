package com.example.baseandroidapp.presentation.users

import com.example.baseandroidapp.domain.users.User

data class UsersUiState(
    val users: List<User> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null,
)
