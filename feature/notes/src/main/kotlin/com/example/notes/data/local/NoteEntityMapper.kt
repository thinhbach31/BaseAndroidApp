package com.example.notes.data.local

import com.example.notes.domain.model.Note

internal fun NoteEntity.toDomain() = Note(
    id = id,
    title = title,
    body = body,
    updatedAt = updatedAt,
)

internal fun Note.toEntity() = NoteEntity(
    id = id,
    title = title,
    body = body,
    updatedAt = updatedAt,
)
