package com.example.baseandroidapp.di

import android.content.Context
import androidx.room.Room
import com.example.baseandroidapp.data.AppDatabase
import com.example.listing.data.local.ListingDao
import com.example.listing.data.remote.ListingApi
import com.example.notes.data.local.NoteDao
import com.example.notes.data.remote.NoteApi
import com.example.recorder.data.local.RecordingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

// Provides AppDatabase + each feature's DAO and Retrofit API.
// Keeping these here lets feature modules stay free of cross-module wiring.
@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app.db").build()

    @Provides fun provideNoteDao(db: AppDatabase): NoteDao = db.noteDao()
    @Provides fun provideRecordingDao(db: AppDatabase): RecordingDao = db.recordingDao()
    @Provides fun provideListingDao(db: AppDatabase): ListingDao = db.listingDao()

    @Provides
    @Singleton
    fun provideNoteApi(retrofit: Retrofit): NoteApi = retrofit.create(NoteApi::class.java)

    @Provides
    @Singleton
    fun provideListingApi(retrofit: Retrofit): ListingApi = retrofit.create(ListingApi::class.java)
}
