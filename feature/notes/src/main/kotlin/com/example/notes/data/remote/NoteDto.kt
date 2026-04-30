package com.example.notes.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class NoteDto(
    val id: String,
    val title: String,
    val body: String,
    val updatedAt: Long,
)
