package com.example.baseandroidapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listing.data.local.ListingDao
import com.example.listing.data.local.ListingEntity
import com.example.notes.data.local.NoteDao
import com.example.notes.data.local.NoteEntity
import com.example.recorder.data.local.RecordingDao
import com.example.recorder.data.local.RecordingEntity

@Database(
    entities = [
        NoteEntity::class,
        RecordingEntity::class,
        ListingEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun recordingDao(): RecordingDao
    abstract fun listingDao(): ListingDao
}
