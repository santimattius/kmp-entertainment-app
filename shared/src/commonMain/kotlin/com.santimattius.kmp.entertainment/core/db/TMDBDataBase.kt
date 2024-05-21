package com.santimattius.kmp.entertainment.core.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class TMDBDataBase : RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao
}