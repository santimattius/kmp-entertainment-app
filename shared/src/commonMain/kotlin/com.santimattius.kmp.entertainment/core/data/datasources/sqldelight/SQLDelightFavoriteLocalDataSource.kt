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
    private val db: AppDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : FavoriteLocalDataSource {

    override val all: Flow<List<Favorite>>
        get() = db.appDatabaseQueries
            .selectAllFavorite()
            .asFlow()
            .mapToList(dispatcher)

}