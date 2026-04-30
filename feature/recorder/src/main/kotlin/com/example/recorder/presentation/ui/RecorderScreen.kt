package com.example.recorder.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recorder.presentation.viewmodel.RecorderViewModel

@Composable
fun RecorderScreen(
    modifier: Modifier = Modifier,
    viewModel: RecorderViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    // TODO: render recorder controls + recordings list, dispatch RecorderUiEvent
    Text(text = state.recorder.toString(), modifier = modifier.fillMaxSize())
}
