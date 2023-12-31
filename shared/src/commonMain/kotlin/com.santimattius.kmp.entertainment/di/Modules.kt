package com.santimattius.kmp.entertainment.di

import com.santimattius.kmp.entertainment.BuildConfig
import com.santimattius.kmp.entertainment.core.data.MovieRepository
import com.santimattius.kmp.entertainment.core.data.TvShowRepository
import com.santimattius.kmp.entertainment.core.network.ktorHttpClient
import com.santimattius.kmp.entertainment.feature.movie.detail.MovieDetailViewModel
import com.santimattius.kmp.entertainment.feature.movie.home.MoviesViewModel
import com.santimattius.kmp.entertainment.feature.tvshow.detail.TvShowDetailViewModel
import com.santimattius.kmp.entertainment.feature.tvshow.home.TvShowViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

enum class Qualifiers {
    API_KEY;
}

val sharedModules = module {

    single(named(Qualifiers.API_KEY)) { BuildConfig.apiKey }
    single { ktorHttpClient(apiKey = get(named(Qualifiers.API_KEY))) }

    single { TvShowRepository(client = get()) }
    single { MovieRepository(client = get()) }

    factory { MoviesViewModel(repository = get()) }
    factory { TvShowViewModel(repository = get()) }

    factory { params -> MovieDetailViewModel(id = params.get(), repository = get()) }
    factory { params -> TvShowDetailViewModel(id = params.get(), repository = get()) }
}

fun appModule() = listOf(sharedModules)