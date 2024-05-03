package com.santimattius.kmp.entertainment.core.data.repositories

import com.santimattius.kmp.entertainment.core.data.datasources.FavoriteLocalDataSource
import com.santimattius.kmp.entertainment.core.domain.Favorite
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(
    private val localDataSource: FavoriteLocalDataSource,
) {

    val all: Flow<List<Favorite>> = localDataSource.all

    suspend fun add(item: Favorite) {
        localDataSource.insert(item)
    }

    suspend fun remove(itemId: Long) {
        localDataSource.delete(itemId)
    }

    suspend fun isFavorite(id: Long): Boolean {
        return localDataSource.findById(id) != null
    }
}