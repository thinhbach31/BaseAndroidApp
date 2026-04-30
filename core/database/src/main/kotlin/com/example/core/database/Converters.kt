package com.example.core.database

import androidx.room.TypeConverter

// Shared Room type converters reusable across feature databases.
// Add converters for non-primitive shared types (e.g., Instant, JSON).
class Converters {
    // @TypeConverter
    // fun fromInstant(value: Instant?): Long? = value?.toEpochMilli()
}
