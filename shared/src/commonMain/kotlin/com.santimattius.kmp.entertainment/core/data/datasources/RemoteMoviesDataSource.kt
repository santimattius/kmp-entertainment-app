package com.santimattius.kmp.entertainment.core.data.datasources

import com.santimattius.kmp.entertainment.core.data.entities.Movie

interface RemoteMoviesDataSource {
    suspend fun getMovies(): Result<List<Movie>>
    suspend fun findById(id: Long): Result<Movie>


}