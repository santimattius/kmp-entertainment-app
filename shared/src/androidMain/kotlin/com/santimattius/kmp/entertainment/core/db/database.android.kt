package com.santimattius.kmp.entertainment.core.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class RoomFactory(private val context: Context) {
    actual fun create(): RoomDatabase.Builder<TMDBDataBase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(DB_NAME)
        return Room.databaseBuilder<TMDBDataBase>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }

    companion object {
        private const val DB_NAME = "the_movie.db"
    }
}