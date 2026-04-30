package com.example.notes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.usecase.CreateNoteUseCase
import com.example.notes.domain.usecase.DeleteNoteUseCase
import com.example.notes.domain.usecase.ObserveNotesUseCase
import com.example.notes.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    observeNotes: ObserveNotesUseCase,
    private val createNote: CreateNoteUseCase,
    private val updateNote: UpdateNoteUseCase,
    private val deleteNote: DeleteNoteUseCase,
) : ViewModel() {

    val state = observeNotes()
        .map { NotesUiState(notes = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
            initialValue = NotesUiState(isLoading = true),
        )

    fun onEvent(event: NotesUiEvent) {
        viewModelScope.launch {
            when (event) {
                is NotesUiEvent.Create -> createNote(event.note)
                is NotesUiEvent.Update -> updateNote(event.note)
                is NotesUiEvent.Delete -> deleteNote(event.id)
            }
        }
    }

    private companion object {
        const val STOP_TIMEOUT_MS = 5_000L
    }
}
