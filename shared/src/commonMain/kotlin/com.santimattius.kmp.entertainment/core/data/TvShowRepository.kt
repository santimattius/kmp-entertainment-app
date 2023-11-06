package com.santimattius.kmp.entertainment.core.data

import com.santimattius.kmp.entertainment.core.domain.TvShow
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class TvShowRepository(private val client: HttpClient) {

    suspend fun getTvShows(): List<TvShow> {
        val response = client.get("tv/popular")
        val result = response.body<TvShowResponse>()
        return result.asTvShows()
    }

    suspend fun findById(id: Long): TvShow {
        val response = client.get("tv/${id}")
        val tvShowDto = response.body<TvShowDto>()
        return tvShowDto.asTvShow()

    }

    private fun TvShowResponse.asTvShows(): List<TvShow> {
        return this.result.map { it.asTvShow() }
    }

    private fun TvShowDto.asTvShow(): TvShow {
        return TvShow(
            id = this.id,
            title = this.name,
            image = this.poster,
            overview = this.overview
        )
    }
}
