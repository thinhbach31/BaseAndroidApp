package com.example.recorder.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recordings")
data class RecordingEntity(
    @PrimaryKey val id: String,
    val filePath: String,
    val durationMs: Long,
    val createdAt: Long,
)
