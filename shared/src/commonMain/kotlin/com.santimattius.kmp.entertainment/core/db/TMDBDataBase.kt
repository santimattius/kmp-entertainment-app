package com.santimattius.kmp.entertainment.core.db

import androidx.room.Database
import androidx.room.RoomDatabase

interface DB {
    fun clearAllTables() {}
}

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class TMDBDataBase : RoomDatabase(), DB {

    abstract fun getFavoriteDao(): FavoriteDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}