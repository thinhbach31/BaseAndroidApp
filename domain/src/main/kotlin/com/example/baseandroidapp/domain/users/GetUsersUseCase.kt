package com.example.baseandroidapp.domain.users

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Returns a stream of users observed from the repository. Use cases keep the
 * presentation layer free of business orchestration logic.
 */
class GetUsersUseCase @Inject constructor(
    private val repository: UsersRepository,
) {
    operator fun invoke(): Flow<List<User>> = repository.observeUsers()
}
