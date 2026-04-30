package com.example.notes.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.notes.presentation.viewmodel.NotesUiEvent
import com.example.notes.presentation.viewmodel.NotesViewModel

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(state.notes, key = { it.id }) { note ->
            // TODO: replace with NoteRow composable
            Text(text = note.title)
        }
    }

    // Sample dispatch:
    // viewModel.onEvent(NotesUiEvent.Delete(note.id))
}
