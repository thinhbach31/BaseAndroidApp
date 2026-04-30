package com.example.notes.data.remote

import com.example.notes.domain.model.Note

internal fun NoteDto.toDomain() = Note(
    id = id,
    title = title,
    body = body,
    updatedAt = updatedAt,
)

internal fun Note.toDto() = NoteDto(
    id = id,
    title = title,
    body = body,
    updatedAt = updatedAt,
)
