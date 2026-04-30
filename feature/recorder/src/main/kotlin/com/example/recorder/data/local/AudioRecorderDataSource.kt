package com.example.recorder.data.local

import com.example.recorder.domain.model.RecorderState
import kotlinx.coroutines.flow.Flow

interface AudioRecorderDataSource {
    val state: Flow<RecorderState>

    /** Begin recording into [outputPath]. */
    suspend fun start(outputPath: String)
    suspend fun pause()
    suspend fun resume()
    /** Stop and return the final duration in millis. */
    suspend fun stop(): Long
}
