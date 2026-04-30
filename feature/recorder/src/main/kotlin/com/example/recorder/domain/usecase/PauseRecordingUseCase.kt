package com.example.recorder.domain.usecase

import com.example.recorder.domain.repository.RecorderRepository
import javax.inject.Inject

class PauseRecordingUseCase @Inject constructor(
    private val repository: RecorderRepository,
) {
    suspend operator fun invoke() = repository.pause()
}
