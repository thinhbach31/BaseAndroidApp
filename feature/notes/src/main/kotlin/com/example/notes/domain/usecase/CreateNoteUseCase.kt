package com.example.notes.domain.usecase

import com.example.notes.domain.model.Note
import com.example.notes.domain.repository.NoteRepository
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(
    private val repository: NoteRepository,
) {
    suspend operator fun invoke(note: Note) = repository.create(note)
}
