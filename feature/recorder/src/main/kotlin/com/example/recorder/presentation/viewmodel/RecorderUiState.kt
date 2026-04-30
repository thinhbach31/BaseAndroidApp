package com.example.recorder.presentation.viewmodel

import com.example.recorder.domain.model.RecorderState
import com.example.recorder.domain.model.Recording

data class RecorderUiState(
    val recorder: RecorderState = RecorderState.Idle,
    val recordings: List<Recording> = emptyList(),
    val error: String? = null,
)
