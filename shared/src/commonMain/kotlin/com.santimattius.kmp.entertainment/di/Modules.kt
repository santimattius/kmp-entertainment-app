package com.santimattius.kmp.entertainment.di

import com.santimattius.kmp.entertainment.BuildConfig
import com.santimattius.kmp.entertainment.core.data.datasources.FavoriteLocalDataSource
import com.santimattius.kmp.entertainment.core.data.datasources.RemoteMoviesDataSource
import com.santimattius.kmp.entertainment.core.data.datasources.RemoteTvShowRemoteDataSource
import com.santimattius.kmp.entertainment.core.data.datasources.ktor.KtorRemoteMoviesDataSource
import com.santimattius.kmp.entertainment.core.data.datasources.ktor.KtorTvShowRemoteDataSource
import com.santimattius.kmp.entertainment.core.data.datasources.room.RoomFavoriteLocalDataSource
import com.santimattius.kmp.entertainment.core.data.repositories.FavoriteRepository
import com.santimattius.kmp.entertainment.core.data.repositories.MovieRepository
import com.santimattius.kmp.entertainment.core.data.repositories.TvShowRepository
import com.santimattius.kmp.entertainment.core.db.TMDBDataBase
import com.santimattius.kmp.entertainment.core.network.ktorHttpClient
import com.santimattius.kmp.entertainment.feature.favorites.FavoriteViewModel
import com.santimattius.kmp.entertainment.feature.movie.detail.MovieDetailViewModel
import com.santimattius.kmp.entertainment.feature.movie.home.MoviesViewModel
import com.santimattius.kmp.entertainment.feature.tvshow.detail.TvShowDetailViewModel
import com.santimattius.kmp.entertainment.feature.tvshow.home.TvShowViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

enum class Qualifiers {
    API_KEY;
}

private val sharedModules = module {

    single(named(Qualifiers.API_KEY)) { BuildConfig.apiKey }
    single { ktorHttpClient(apiKey = get(named(Qualifiers.API_KEY))) }

    single<RemoteMoviesDataSource> { KtorRemoteMoviesDataSource(client = get()) }
    single<RemoteTvShowRemoteDataSource> { KtorTvShowRemoteDataSource(client = get()) }

    single<FavoriteLocalDataSource> { RoomFavoriteLocalDataSource(get<TMDBDataBase>().getFavoriteDao()) }

    single { TvShowRepository(remoteTvShowRemoteDataSource = get()) }
    single { MovieRepository(remoteMoviesDataSource = get()) }
    single { FavoriteRepository(localDataSource = get()) }

}

private val featureModules = module {
    viewModelOf(::MoviesViewModel)
    viewModelOf(::TvShowViewModel)

    viewModelOf(::MovieDetailViewModel)
    viewModelOf(::TvShowDetailViewModel)
    viewModelOf(::FavoriteViewModel)
}

expect val platformModule: Module

fun appModule() = listOf(featureModules, platformModule, sharedModules, navigationModule)