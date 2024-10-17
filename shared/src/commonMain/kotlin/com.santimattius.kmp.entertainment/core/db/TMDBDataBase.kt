package com.santimattius.kmp.entertainment.core.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

interface DB {
    fun clearAllTables() {}
}

@Database(entities = [FavoriteEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class TMDBDataBase : RoomDatabase(), DB {

    abstract fun getFavoriteDao(): FavoriteDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<TMDBDataBase> {
    override fun initialize(): TMDBDataBase
}