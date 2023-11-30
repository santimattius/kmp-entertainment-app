package com.santimattius.kmp.entertainment.core.data.datasources

import com.santimattius.kmp.entertainment.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteLocalDataSource {

    val all: Flow<List<Favorite>>
}