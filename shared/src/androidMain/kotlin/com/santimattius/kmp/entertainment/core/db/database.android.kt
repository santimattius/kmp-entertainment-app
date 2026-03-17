package com.santimattius.kmp.entertainment.core.db

import android.content.Context
import androidx.room3.Room
import androidx.room3.RoomDatabase

actual class RoomFactory(private val context: Context) {
    actual fun create(): RoomDatabase.Builder<TMDBDataBase> {
        val appContext = context.applicationContext
        val dbPath = appContext.getDatabasePath(DB_NAME).absolutePath
        return Room.databaseBuilder<TMDBDataBase>(context = appContext, name = dbPath)
    }

    companion object {
        private const val DB_NAME = "the_movie.db"
    }
}