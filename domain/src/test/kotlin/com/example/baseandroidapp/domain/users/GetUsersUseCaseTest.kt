package com.example.baseandroidapp.domain.users

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUsersUseCaseTest {

    @Test
    fun `invoke returns the flow exposed by the repository`() = runBlocking {
        val expected = listOf(User(1, "Ada", "ada@example.com"))
        val useCase = GetUsersUseCase(FakeRepository(flowOf(expected)))

        val result = useCase().first()

        assertEquals(expected, result)
    }

    private class FakeRepository(private val flow: Flow<List<User>>) : UsersRepository {
        override fun observeUsers(): Flow<List<User>> = flow
        override suspend fun refresh() = Unit
    }
}
