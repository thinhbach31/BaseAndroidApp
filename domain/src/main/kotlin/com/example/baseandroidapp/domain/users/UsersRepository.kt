package com.example.baseandroidapp.domain.users

import kotlinx.coroutines.flow.Flow

/**
 * Repository contract for [User] data. The implementation lives in `:data` —
 * the domain layer never depends on it directly, only on this interface.
 */
interface UsersRepository {
    fun observeUsers(): Flow<List<User>>
    suspend fun refresh()
}
