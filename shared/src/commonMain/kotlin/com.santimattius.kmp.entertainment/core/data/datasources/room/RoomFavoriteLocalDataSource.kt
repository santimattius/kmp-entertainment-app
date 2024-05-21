package com.santimattius.kmp.entertainment.core.data.datasources.room

import com.santimattius.kmp.entertainment.core.data.datasources.FavoriteLocalDataSource
import com.santimattius.kmp.entertainment.core.db.FavoriteDao
import com.santimattius.kmp.entertainment.core.db.FavoriteEntity
import com.santimattius.kmp.entertainment.core.domain.ContentType
import com.santimattius.kmp.entertainment.core.domain.Favorite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RoomFavoriteLocalDataSource(
    private val dao: FavoriteDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : FavoriteLocalDataSource {
    override val all: Flow<List<Favorite>>
        get() = dao.getAllAsFlow().map { favorites ->
            favorites.map {
                Favorite(
                    resourceId = it.resourceId,
                    title = it.title,
                    overview = it.overview,
                    imageUrl = it.imageUrl,
                    type = ContentType.valueOf(it.type)
                )
            }
        }.flowOn(dispatcher)

    override suspend fun insert(favorite: Favorite) {
        withContext(dispatcher) {
            val entity = FavoriteEntity(
                resourceId = favorite.resourceId,
                title = favorite.title,
                overview = favorite.overview,
                imageUrl = favorite.imageUrl,
                type = favorite.type.name
            )
            dao.insert(entity)
        }
    }

    override suspend fun delete(resourceId: Long) {
        withContext(dispatcher) { dao.deleteById(resourceId.toInt()) }
    }

    override suspend fun findById(resourceId: Long): Favorite? {
        return withContext(dispatcher) {
            val entity = dao.findById(resourceId.toInt())?: return@withContext null
            Favorite(
                resourceId = entity.resourceId,
                title = entity.title,
                overview = entity.overview,
                imageUrl = entity.imageUrl,
                type = ContentType.valueOf(entity.type)
            )
        }
    }
}