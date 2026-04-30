package com.example.recorder.domain.repository

import com.example.recorder.domain.model.RecorderState
import com.example.recorder.domain.model.Recording
import kotlinx.coroutines.flow.Flow

interface RecorderRepository {
    val state: Flow<RecorderState>

    fun observeRecordings(): Flow<List<Recording>>

    suspend fun start()
    suspend fun pause()
    suspend fun resume()
    suspend fun stop(): Recording
}
