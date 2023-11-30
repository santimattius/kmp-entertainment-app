package com.santimattius.kmp.entertainment.core.data.repositories

import com.santimattius.kmp.entertainment.core.data.datasources.FavoriteLocalDataSource
import com.santimattius.kmp.entertainment.core.domain.Favorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.santimattius.kmp.entertainment.Favorite as FavoriteEntity

class FavoriteRepository(
    private val localDataSource: FavoriteLocalDataSource,
) {

    val all: Flow<List<Favorite>> = localDataSource.all.map { values ->
        values.map { item ->
            Favorite(
                resourceId = item.resourceId,
                title = item.title,
                overview = item.overview,
                imageUrl = item.imageUrl
            )
        }
    }

    fun add(item: Favorite) {
        val entity = FavoriteEntity(
            resourceId = item.resourceId,
            title = item.title,
            overview = item.overview,
            imageUrl = item.imageUrl
        )
        localDataSource.insert(entity)
    }

    fun remove(itemId: Long) {
        localDataSource.delete(itemId)
    }

    fun isFavorite(id: Long): Boolean {
        return localDataSource.findById(id) != null
    }
}