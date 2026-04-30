package com.example.notes.data.repository

import com.example.notes.data.local.NoteDao
import com.example.notes.data.local.toDomain
import com.example.notes.data.local.toEntity
import com.example.notes.data.remote.NoteApi
import com.example.notes.domain.model.Note
import com.example.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
    private val api: NoteApi,
) : NoteRepository {

    override fun observeAll(): Flow<List<Note>> =
        dao.observeAll().map { rows -> rows.map { it.toDomain() } }

    override suspend fun create(note: Note) {
        // TODO: also push to api when online
        dao.upsert(note.toEntity())
    }

    override suspend fun update(note: Note) {
        dao.upsert(note.toEntity())
    }

    override suspend fun delete(id: String) {
        dao.deleteById(id)
    }
}
