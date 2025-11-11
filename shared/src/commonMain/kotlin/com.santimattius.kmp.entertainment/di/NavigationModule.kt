package com.santimattius.kmp.entertainment.di

import com.santimattius.kmp.entertainment.Navigator
import com.santimattius.kmp.entertainment.core.domain.ContentType
import com.santimattius.kmp.entertainment.core.extensions.koinViewModel
import com.santimattius.kmp.entertainment.feature.favorites.FavoriteRoute
import com.santimattius.kmp.entertainment.feature.movie.detail.MovieDetailScene
import com.santimattius.kmp.entertainment.feature.movie.detail.MovieDetailViewModel
import com.santimattius.kmp.entertainment.feature.movie.home.MoviesScene
import com.santimattius.kmp.entertainment.feature.movie.home.MoviesViewModel
import com.santimattius.kmp.entertainment.feature.splash.SplashScreen
import com.santimattius.kmp.entertainment.feature.tvshow.detail.TvShowDetailScene
import com.santimattius.kmp.entertainment.feature.tvshow.detail.TvShowDetailViewModel
import com.santimattius.kmp.entertainment.feature.tvshow.home.TvShowScene
import com.santimattius.kmp.entertainment.navigation.Favorite
import com.santimattius.kmp.entertainment.navigation.HomeWebPage
import com.santimattius.kmp.entertainment.navigation.Movie
import com.santimattius.kmp.entertainment.navigation.MovieDetail
import com.santimattius.kmp.entertainment.navigation.Splash
import com.santimattius.kmp.entertainment.navigation.TvShow
import com.santimattius.kmp.entertainment.navigation.TvShowDetail
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val navigationModule = module {
    single<Navigator> { param ->
        Navigator(param.get(), param.get())
    }

    navigation<Splash> {
        val state: Navigator = get()
        SplashScreen {
            state.onUpClick()
            state.onNavClick(Movie)
        }
    }

    navigation<Movie> {
        val state: Navigator = get()
        MoviesScene(
            viewModel = koinViewModel<MoviesViewModel>(),
            onMovieClick = { id ->
                state.onNavClick(MovieDetail(id))
            }
        )
    }
    navigation<MovieDetail> { key ->
        val state: Navigator = get()
        MovieDetailScene(
            viewModel = koinViewModel<MovieDetailViewModel> { parametersOf(key.id) },
            navigateToWebPage = {
                state.onNavClick(HomeWebPage(it))
            }
        )
    }
    navigation<TvShow> {
        val state: Navigator = get()
        TvShowScene(
            viewModel = koinViewModel(),
            onMovieClick = { id ->
                state.onNavClick(TvShowDetail(id))
            }
        )
    }
    navigation<TvShowDetail> { key ->
        TvShowDetailScene(
            viewModel = koinViewModel<TvShowDetailViewModel> {
                parametersOf(key.id)
            }
        )
    }
    navigation<Favorite> {
        val state: Navigator = get()
        FavoriteRoute {
            when (it.type) {
                ContentType.MOVIE -> {
                    state.onNavClick(MovieDetail(it.id))
                }

                ContentType.TV -> {
                    state.onNavClick(TvShowDetail(it.id))
                }
            }
        }
    }
    navigation<HomeWebPage> { key ->
        HomeWebPage(key.url)
    }
}