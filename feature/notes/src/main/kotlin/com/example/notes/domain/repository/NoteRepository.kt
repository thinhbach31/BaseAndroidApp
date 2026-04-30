package com.example.notes.domain.repository

import com.example.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun observeAll(): Flow<List<Note>>
    suspend fun create(note: Note)
    suspend fun update(note: Note)
    suspend fun delete(id: String)
}
