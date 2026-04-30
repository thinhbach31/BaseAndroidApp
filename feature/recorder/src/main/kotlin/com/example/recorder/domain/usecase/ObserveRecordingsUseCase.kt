package com.example.recorder.domain.usecase

import com.example.recorder.domain.repository.RecorderRepository
import javax.inject.Inject

class ObserveRecordingsUseCase @Inject constructor(
    private val repository: RecorderRepository,
) {
    operator fun invoke() = repository.observeRecordings()
}
