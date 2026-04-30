package com.example.notes.data.di

import com.example.notes.data.repository.NoteRepositoryImpl
import com.example.notes.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Only repository binding lives here. The DAO and Retrofit API are provided
// by :app's AppDataModule, which owns AppDatabase and Retrofit instances.
@Module
@InstallIn(SingletonComponent::class)
abstract class NotesDataModule {
    @Binds
    @Singleton
    abstract fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository
}
