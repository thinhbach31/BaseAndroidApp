package com.example.recorder.presentation.viewmodel

sealed interface RecorderUiEvent {
    data object Start : RecorderUiEvent
    data object Pause : RecorderUiEvent
    data object Resume : RecorderUiEvent
    data object Stop : RecorderUiEvent
}
