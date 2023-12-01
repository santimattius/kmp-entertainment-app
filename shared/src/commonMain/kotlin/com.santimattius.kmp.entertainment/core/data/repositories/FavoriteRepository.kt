package com.santimattius.kmp.entertainment.core.data.repositories

import com.santimattius.kmp.entertainment.core.data.datasources.FavoriteLocalDataSource
import com.santimattius.kmp.entertainment.core.domain.ContentType
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
                imageUrl = item.imageUrl,
                type = ContentType.valueOf(item.type)
            )
        }
    }

    fun add(item: Favorite) {
        val entity = FavoriteEntity(
            resourceId = item.resourceId,
            title = item.title,
            overview = item.overview,
            imageUrl = item.imageUrl,
            type = item.type.name,
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