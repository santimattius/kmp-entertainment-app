package com.santimattius.kmp.entertainment.core.db

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

actual class RoomFactory {
    actual fun create(): RoomDatabase.Builder<TMDBDataBase> {
        val dbFilePath = NSHomeDirectory() + "/${DB_NAME}"
        return Room.databaseBuilder<TMDBDataBase>(
            name = dbFilePath,
            factory = { TMDBDataBase::class.instantiateImpl() }
        )
    }

    companion object {
        private const val DB_NAME = "the_movie.db"
    }
}