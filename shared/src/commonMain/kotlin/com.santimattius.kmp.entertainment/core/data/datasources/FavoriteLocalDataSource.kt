package com.santimattius.kmp.entertainment.core.data.datasources

import com.santimattius.kmp.entertainment.core.domain.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteLocalDataSource {

    val all: Flow<List<Favorite>>

    suspend fun insert(favorite: Favorite)
    suspend fun delete(resourceId: Long)
    suspend fun findById(resourceId: Long): Favorite?
}