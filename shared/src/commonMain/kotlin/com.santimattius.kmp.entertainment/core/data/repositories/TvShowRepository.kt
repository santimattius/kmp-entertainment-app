package com.santimattius.kmp.entertainment.core.data.repositories

import com.santimattius.kmp.entertainment.core.data.datasources.RemoteTvShowRemoteDataSource
import com.santimattius.kmp.entertainment.core.data.entities.asTvShow
import com.santimattius.kmp.entertainment.core.data.entities.asTvShows
import com.santimattius.kmp.entertainment.core.domain.TvShow

class TvShowRepository(
    private val remoteTvShowRemoteDataSource: RemoteTvShowRemoteDataSource,
) {

    suspend fun getTvShows(): List<TvShow> {
        val result = remoteTvShowRemoteDataSource.getTvShows().getOrThrow()
        return result.asTvShows()
    }

    suspend fun findById(id: Long): TvShow {
        val tvShowDto = remoteTvShowRemoteDataSource.findById(id).getOrThrow()
        return tvShowDto.asTvShow()

    }
}
