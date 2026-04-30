package com.example.recorder.data.local

import android.content.Context
import com.example.recorder.domain.model.RecorderState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaRecorderDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) : AudioRecorderDataSource {

    private val _state = MutableStateFlow<RecorderState>(RecorderState.Idle)
    override val state: Flow<RecorderState> = _state.asStateFlow()

    override suspend fun start(outputPath: String) {
        // TODO: configure MediaRecorder (audio source, encoder, output file).
        _state.value = RecorderState.Recording(elapsedMs = 0L)
    }

    override suspend fun pause() {
        // TODO: MediaRecorder.pause() (API 24+)
        val current = _state.value
        if (current is RecorderState.Recording) {
            _state.value = RecorderState.Paused(current.elapsedMs)
        }
    }

    override suspend fun resume() {
        // TODO: MediaRecorder.resume()
        val current = _state.value
        if (current is RecorderState.Paused) {
            _state.value = RecorderState.Recording(current.elapsedMs)
        }
    }

    override suspend fun stop(): Long {
        // TODO: stop, release, return measured duration.
        val elapsed = (_state.value as? RecorderState.Recording)?.elapsedMs
            ?: (_state.value as? RecorderState.Paused)?.elapsedMs
            ?: 0L
        _state.value = RecorderState.Idle
        return elapsed
    }
}
