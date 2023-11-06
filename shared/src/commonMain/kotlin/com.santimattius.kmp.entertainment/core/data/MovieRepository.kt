package com.santimattius.kmp.entertainment.core.data

import com.santimattius.kmp.entertainment.core.domain.Movie
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import com.santimattius.kmp.entertainment.core.data.Movie as MovieDto

class MovieRepository(private val client: HttpClient) {

    suspend fun getMovies(): List<Movie> {
        val response = client.get("movie/popular")
        val result = response.body<MovieResponse>()
        return result.asMovies()
    }

    suspend fun findById(id: Long): Movie {
        val response = client.get("movie/${id}")
        val result = response.body<MovieDto>()
        return result.asMovie()

    }

    private fun MovieResponse.asMovies(): List<Movie> {
        return this.results.map { it.asMovie() }
    }

    private fun MovieDto.asMovie(): Movie {
        return Movie(
            id = this.id,
            title = this.title,
            image = this.poster,
            overview = this.overview
        )
    }
}
