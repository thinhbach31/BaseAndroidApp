package com.example.recorder.data.local

import com.example.recorder.domain.model.Recording

internal fun RecordingEntity.toDomain() = Recording(
    id = id,
    filePath = filePath,
    durationMs = durationMs,
    createdAt = createdAt,
)

internal fun Recording.toEntity() = RecordingEntity(
    id = id,
    filePath = filePath,
    durationMs = durationMs,
    createdAt = createdAt,
)
