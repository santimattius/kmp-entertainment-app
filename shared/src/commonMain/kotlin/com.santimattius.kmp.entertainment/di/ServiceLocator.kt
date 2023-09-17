package com.santimattius.kmp.entertainment.di

import com.santimattius.kmp.entertainment.core.data.MovieRepository
import com.santimattius.kmp.entertainment.core.data.TvShowRepository
import com.santimattius.kmp.entertainment.core.network.ktorHttpClient
import com.santimattius.kmp.entertainment.feature.movie.detail.MovieDetailViewModel
import com.santimattius.kmp.entertainment.feature.movie.home.MoviesViewModel
import com.santimattius.kmp.entertainment.feature.tvshow.detail.TvShowDetailViewModel
import com.santimattius.kmp.entertainment.feature.tvshow.home.TvShowViewModel
import io.ktor.client.HttpClient
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object ServiceLocator {

    private const val API_KEY = "ee6fa3652297841e02d5808229a45d6d"

    private var client: HttpClient? = null
    private var movieRepository: MovieRepository? = null
    private var tvShowRepository: TvShowRepository? = null


    fun provideMoviesViewModel(): MoviesViewModel {
        return MoviesViewModel(provideMovieRepository())
    }

    fun provideTvShowsViewModel(): TvShowViewModel {
        return TvShowViewModel(provideTvShowRepository())
    }

    fun provideMovieDetailViewModel(id: Long): MovieDetailViewModel {
        return MovieDetailViewModel(id, provideMovieRepository())
    }

    fun provideTvShowDetailViewModel(id: Long): TvShowDetailViewModel {
        return TvShowDetailViewModel(id, provideTvShowRepository())
    }

    private fun provideMovieRepository(): MovieRepository {
        return movieRepository ?: createMovieRepository()
    }

    private fun provideTvShowRepository(): TvShowRepository {
        return tvShowRepository ?: createTvShowRepository()
    }


    private fun createMovieRepository(): MovieRepository {
        val newRepository = MovieRepository(provideHttpClient())
        movieRepository = newRepository
        return newRepository
    }

    private fun createTvShowRepository(): TvShowRepository {
        val newRepository = TvShowRepository(provideHttpClient())
        tvShowRepository = newRepository
        return newRepository
    }

    private fun provideHttpClient(): HttpClient {
        return client ?: createHttpClient()
    }

    private fun createHttpClient(): HttpClient {
        val newClient = ktorHttpClient(apiKey = API_KEY)
        client = newClient
        return newClient
    }
}