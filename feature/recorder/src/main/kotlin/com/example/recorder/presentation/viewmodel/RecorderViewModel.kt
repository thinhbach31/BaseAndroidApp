package com.example.recorder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recorder.domain.repository.RecorderRepository
import com.example.recorder.domain.usecase.ObserveRecordingsUseCase
import com.example.recorder.domain.usecase.PauseRecordingUseCase
import com.example.recorder.domain.usecase.ResumeRecordingUseCase
import com.example.recorder.domain.usecase.StartRecordingUseCase
import com.example.recorder.domain.usecase.StopRecordingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecorderViewModel @Inject constructor(
    repository: RecorderRepository,
    observeRecordings: ObserveRecordingsUseCase,
    private val start: StartRecordingUseCase,
    private val pause: PauseRecordingUseCase,
    private val resume: ResumeRecordingUseCase,
    private val stop: StopRecordingUseCase,
) : ViewModel() {

    val state = combine(repository.state, observeRecordings()) { recorderState, recordings ->
        RecorderUiState(recorder = recorderState, recordings = recordings)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
        initialValue = RecorderUiState(),
    )

    fun onEvent(event: RecorderUiEvent) {
        viewModelScope.launch {
            when (event) {
                RecorderUiEvent.Start -> start()
                RecorderUiEvent.Pause -> pause()
                RecorderUiEvent.Resume -> resume()
                RecorderUiEvent.Stop -> stop()
            }
        }
    }

    private companion object {
        const val STOP_TIMEOUT_MS = 5_000L
    }
}
