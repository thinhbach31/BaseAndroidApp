package com.example.recorder.domain.model

data class Recording(
    val id: String,
    val filePath: String,
    val durationMs: Long,
    val createdAt: Long,
)
