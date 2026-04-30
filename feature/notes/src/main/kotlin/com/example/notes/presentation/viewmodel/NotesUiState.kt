package com.example.notes.presentation.viewmodel

import com.example.notes.domain.model.Note

data class NotesUiState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
