package com.example.recorder.data.repository

import android.content.Context
import com.example.recorder.data.local.AudioRecorderDataSource
import com.example.recorder.data.local.RecordingDao
import com.example.recorder.data.local.toDomain
import com.example.recorder.data.local.toEntity
import com.example.recorder.domain.model.RecorderState
import com.example.recorder.domain.model.Recording
import com.example.recorder.domain.repository.RecorderRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File
import java.util.UUID
import javax.inject.Inject

class RecorderRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val recorder: AudioRecorderDataSource,
    private val dao: RecordingDao,
) : RecorderRepository {

    private var pendingFilePath: String? = null

    override val state: Flow<RecorderState> = recorder.state

    override fun observeRecordings(): Flow<List<Recording>> =
        dao.observeAll().map { rows -> rows.map { it.toDomain() } }

    override suspend fun start() {
        val path = nextFilePath()
        pendingFilePath = path
        recorder.start(path)
    }

    override suspend fun pause() = recorder.pause()
    override suspend fun resume() = recorder.resume()

    override suspend fun stop(): Recording {
        val durationMs = recorder.stop()
        val recording = Recording(
            id = UUID.randomUUID().toString(),
            filePath = pendingFilePath.orEmpty(),
            durationMs = durationMs,
            createdAt = System.currentTimeMillis(),
        )
        pendingFilePath = null
        dao.upsert(recording.toEntity())
        return recording
    }

    private fun nextFilePath(): String =
        File(context.filesDir, "rec-${System.currentTimeMillis()}.m4a").absolutePath
}
