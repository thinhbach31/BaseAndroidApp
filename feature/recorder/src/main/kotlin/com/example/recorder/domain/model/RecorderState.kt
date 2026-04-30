package com.example.recorder.domain.model

sealed interface RecorderState {
    data object Idle : RecorderState
    data class Recording(val elapsedMs: Long) : RecorderState
    data class Paused(val elapsedMs: Long) : RecorderState
}
