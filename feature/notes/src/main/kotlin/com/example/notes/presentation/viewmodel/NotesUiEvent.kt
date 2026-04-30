package com.example.notes.presentation.viewmodel

import com.example.notes.domain.model.Note

sealed interface NotesUiEvent {
    data class Create(val note: Note) : NotesUiEvent
    data class Update(val note: Note) : NotesUiEvent
    data class Delete(val id: String) : NotesUiEvent
}
