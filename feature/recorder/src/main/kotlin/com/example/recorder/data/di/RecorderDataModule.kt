package com.example.recorder.data.di

import com.example.recorder.data.local.AudioRecorderDataSource
import com.example.recorder.data.local.MediaRecorderDataSource
import com.example.recorder.data.repository.RecorderRepositoryImpl
import com.example.recorder.domain.repository.RecorderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// RecordingDao is provided in :app's AppDataModule via AppDatabase.recordingDao().
@Module
@InstallIn(SingletonComponent::class)
abstract class RecorderDataModule {
    @Binds
    @Singleton
    abstract fun bindRepository(impl: RecorderRepositoryImpl): RecorderRepository

    @Binds
    @Singleton
    abstract fun bindAudioRecorder(impl: MediaRecorderDataSource): AudioRecorderDataSource
}
