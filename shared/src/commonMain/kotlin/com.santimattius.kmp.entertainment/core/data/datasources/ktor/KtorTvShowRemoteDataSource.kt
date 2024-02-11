package com.santimattius.kmp.entertainment.core.data.datasources.ktor

import com.santimattius.kmp.entertainment.core.data.datasources.RemoteTvShowRemoteDataSource
import com.santimattius.kmp.entertainment.core.data.entities.TvShowDto
import com.santimattius.kmp.entertainment.core.data.entities.TvShowResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorTvShowRemoteDataSource(
    private val client: HttpClient,
) : RemoteTvShowRemoteDataSource {

    override suspend fun getTvShows(): Result<List<TvShowDto>> = runCatching {
        val response = client.get("tv/popular")
        val result = response.body<TvShowResponse>()
        result.result
    }

    override suspend fun findById(id: Long): Result<TvShowDto> = runCatching {
        val response = client.get("tv/${id}")
        response.body()
    }

}
