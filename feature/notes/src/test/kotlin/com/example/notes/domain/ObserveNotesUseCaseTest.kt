package com.example.notes.domain

import app.cash.turbine.test
import com.example.notes.domain.model.Note
import com.example.notes.domain.repository.NoteRepository
import com.example.notes.domain.usecase.ObserveNotesUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ObserveNotesUseCaseTest {

    private val repository: NoteRepository = mockk()
    private val useCase = ObserveNotesUseCase(repository)

    @Test
    fun `emits notes from repository`() = runTest {
        val notes = listOf(Note(id = "1", title = "t", body = "b", updatedAt = 0L))
        every { repository.observeAll() } returns flowOf(notes)

        useCase().test {
            assertEquals(notes, awaitItem())
            awaitComplete()
        }
    }
}
