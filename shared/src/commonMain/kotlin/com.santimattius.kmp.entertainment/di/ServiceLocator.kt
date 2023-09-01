package com.santimattius.kmp.entertainment.di

import com.santimattius.kmp.entertainment.core.data.MovieRepository
import com.santimattius.kmp.entertainment.core.network.ktorHttpClient
import com.santimattius.kmp.entertainment.feature.detail.DetailViewModel
import com.santimattius.kmp.entertainment.feature.home.HomeViewModel
import io.ktor.client.HttpClient
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object ServiceLocator {

    private const val API_KEY = ""

    private var client: HttpClient? = null
    private var repository: MovieRepository? = null

    fun provideHomeViewModel(): HomeViewModel {
        return HomeViewModel(provideMovieRepository())
    }

    fun provideDetailViewModel(id: Long): DetailViewModel {
        return DetailViewModel(id, provideMovieRepository())
    }

    private fun provideMovieRepository(): MovieRepository {
        return repository ?: createMovieRepository()
    }

    private fun createMovieRepository(): MovieRepository {
        val newRepository = MovieRepository(provideHttpClient())
        repository = newRepository
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