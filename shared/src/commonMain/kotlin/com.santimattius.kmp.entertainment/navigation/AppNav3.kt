package com.santimattius.kmp.entertainment.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.scene.rememberSceneSetupNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.santimattius.kmp.entertainment.core.domain.ContentType
import com.santimattius.kmp.entertainment.feature.favorites.FavoriteRoute
import com.santimattius.kmp.entertainment.feature.movie.detail.MovieDetailScene
import com.santimattius.kmp.entertainment.feature.movie.detail.MovieDetailViewModel
import com.santimattius.kmp.entertainment.feature.movie.home.MoviesRoute
import com.santimattius.kmp.entertainment.feature.splash.SplashScreen
import com.santimattius.kmp.entertainment.feature.tvshow.detail.TvShowDetailScene
import com.santimattius.kmp.entertainment.feature.tvshow.detail.TvShowDetailViewModel
import com.santimattius.kmp.entertainment.feature.tvshow.home.TvShowRoute

@Composable
fun AppNav3(
    modifier: Modifier = Modifier,
    backStack: SnapshotStateList<Any>,
    onNavClick: (Any) -> Unit,
    onBack: () -> Unit,
) {
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
        ),
        entryProvider = entryProvider{
            entry<Splash> {
                SplashScreen {
                    onBack()
                    onNavClick(Movie)
                }
            }

            entry<Movie> {
                MoviesRoute { id ->
                    onNavClick(MovieDetail(id))
                }
            }
            entry<MovieDetail> { key ->
                MovieDetailScene(viewModel = viewModel(factory = MovieDetailViewModel.Factory(key))) {
                    onNavClick(HomeWebPage(it))
                }
            }
            entry<TvShow> {
                TvShowRoute { id ->
                    onNavClick(TvShowDetail(id))
                }
            }
            entry<TvShowDetail> { key ->
                TvShowDetailScene(viewModel = viewModel(factory = TvShowDetailViewModel.Factory(key)))
            }
            entry<Favorite> {
                FavoriteRoute{
                    when(it.type){
                        ContentType.MOVIE -> {
                            onNavClick(MovieDetail(it.id))
                        }
                        ContentType.TV -> {
                            onNavClick(TvShowDetail(it.id))
                        }
                    }
                }
            }
            entry<HomeWebPage> { key ->
                HomeWebPage(key.url)
            }
        }
        /*entryProvider = { key ->
            when (key) {
                is Splash -> NavEntry(key) {
                    SplashScreen {
                        onBack()
                        onNavClick(Movie)
                    }
                }

                is Movie -> NavEntry(key) {
                    MoviesRoute { id ->
                        onNavClick(MovieDetail(id))
                    }
                }

                is MovieDetail -> NavEntry(key) {
                    MovieDetailScene(
                        viewModel = viewModel(factory = MovieDetailViewModel.Factory(key)),
                    ) { url ->
                        onNavClick(HomeWebPage(url))
                    }
                }

                is TvShow -> NavEntry(key) {
                    TvShowRoute { id ->
                        onNavClick(TvShowDetail(id))
                    }
                }

                is TvShowDetail -> NavEntry(key) {
                    TvShowDetailScene(viewModel = viewModel(factory =TvShowDetailViewModel.Factory(key)))
                }

                is Favorite -> NavEntry(key) {
                    FavoriteRoute {
                        when (it.type) {
                            ContentType.MOVIE -> {
                                onNavClick(MovieDetail(it.id))
                            }

                            ContentType.TV -> {
                                onNavClick(TvShowDetail(it.id))
                            }
                        }
                    }
                }

                is HomeWebPage -> NavEntry(key) {
                    HomeWebPage(key.url)
                }


                else -> {
                    error("Unknown route: $key")
                }
            }
        }*/
    )
}