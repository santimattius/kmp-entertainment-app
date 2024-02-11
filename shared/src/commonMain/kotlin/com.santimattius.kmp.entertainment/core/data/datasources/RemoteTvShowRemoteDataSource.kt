package com.santimattius.kmp.entertainment.core.data.datasources

import com.santimattius.kmp.entertainment.core.data.entities.TvShowDto
import com.santimattius.kmp.entertainment.core.domain.TvShow

interface RemoteTvShowRemoteDataSource {
    suspend fun getTvShows(): Result<List<TvShowDto>>

    suspend fun findById(id: Long): Result<TvShowDto>
}