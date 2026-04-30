package com.example.notes.domain.usecase

import com.example.notes.domain.repository.NoteRepository
import javax.inject.Inject

class ObserveNotesUseCase @Inject constructor(
    private val repository: NoteRepository,
) {
    operator fun invoke() = repository.observeAll()
}
