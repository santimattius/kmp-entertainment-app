package com.santimattius.kmp.entertainment.core.data.datasources.ktor

import com.santimattius.kmp.entertainment.core.data.datasources.RemoteMoviesDataSource
import com.santimattius.kmp.entertainment.core.data.entities.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import com.santimattius.kmp.entertainment.core.data.entities.Movie as MovieDto

class KtorRemoteMoviesDataSource(
    private val client: HttpClient,
) : RemoteMoviesDataSource {
    override suspend fun getMovies(): Result<List<MovieDto>> = runCatching {
        val response = client.get("movie/popular")
        val result = response.body<MovieResponse>()
        result.results
    }

    override suspend fun findById(id: Long): Result<MovieDto> = runCatching {
        val response = client.get("movie/${id}")
        response.body<MovieDto>()
    }
}