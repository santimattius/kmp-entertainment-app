package com.santimattius.kmp.entertainment.core.data.datasources.sqldelight

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.santimattius.kmp.entertainment.AppDatabase
import com.santimattius.kmp.entertainment.Favorite
import com.santimattius.kmp.entertainment.core.data.datasources.FavoriteLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class SQLDelightFavoriteLocalDataSource(
    db: AppDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : FavoriteLocalDataSource {

    private val databaseQueries = db.appDatabaseQueries

    override val all: Flow<List<Favorite>>
        get() = databaseQueries
            .selectAllFavorite()
            .asFlow()
            .mapToList(dispatcher)

    override fun insert(favorite: Favorite) {
        val (resourceId, title, overview, imageUrl) = favorite
        databaseQueries.insertFavorite(
            resourceId = resourceId,
            title = title,
            overview = overview,
            imageUrl = imageUrl
        )
    }

    override fun delete(resourceId: Long) {
        databaseQueries.deleteFavoriteById(resourceId)
    }

    override fun findById(resourceId: Long): Favorite? {
        return databaseQueries.selectFavoriteById(resourceId).executeAsOneOrNull()
    }
}