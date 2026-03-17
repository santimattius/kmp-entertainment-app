package com.santimattius.kmp.entertainment.core.db

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor

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

// Room 3 compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<TMDBDataBase> {
    override fun initialize(): TMDBDataBase
}