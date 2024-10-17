package com.santimattius.kmp.entertainment.core.db

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class RoomFactory {
    actual fun create(): RoomDatabase.Builder<TMDBDataBase> {
        val dbFilePath = documentDirectory()  + "/${DB_NAME}"
        return Room.databaseBuilder<TMDBDataBase>(
            name = dbFilePath,
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory?.path)
    }

    companion object {
        private const val DB_NAME = "the_movie.db"
    }
}