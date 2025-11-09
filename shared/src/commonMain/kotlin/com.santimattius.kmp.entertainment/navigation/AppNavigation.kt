package com.santimattius.kmp.entertainment.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
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
fun AppNavigation(
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
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
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
            entry<HomeWebPage> { key ->
                HomeWebPage(key.url)
            }
        }
    )
}