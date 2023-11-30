package com.santimattius.kmp.entertainment.core.data.datasources

import com.santimattius.kmp.entertainment.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteLocalDataSource {

    val all: Flow<List<Favorite>>

    fun insert(favorite: Favorite)
    fun delete(resourceId: Long)
    fun findById(resourceId: Long): Favorite?
}