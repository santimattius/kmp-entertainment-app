package com.santimattius.kmp.entertainment.core.data.repositories

import com.santimattius.kmp.entertainment.core.data.datasources.RemoteMoviesDataSource
import com.santimattius.kmp.entertainment.core.data.entities.asMovie
import com.santimattius.kmp.entertainment.core.data.entities.asMovies
import com.santimattius.kmp.entertainment.core.domain.Movie
import com.santimattius.kmp.entertainment.core.data.entities.Movie as MovieDto

class MovieRepository(
    private val remoteMoviesDataSource: RemoteMoviesDataSource,
) {

    suspend fun getMovies(): List<Movie> {
        val result: List<MovieDto> = remoteMoviesDataSource.getMovies().getOrThrow()
        return result.asMovies()
    }

    suspend fun findById(id: Long): Movie {
        val result = remoteMoviesDataSource.findById(id).getOrThrow()
        return result.asMovie()
    }

}
