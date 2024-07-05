package com.santimattius.kmp.entertainment.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.santimattius.kmp.entertainment.LocalNavAnimatedVisibilityScope
import com.santimattius.kmp.entertainment.LocalSharedTransitionScope
import com.santimattius.kmp.entertainment.core.domain.ContentType
import com.santimattius.kmp.entertainment.feature.favorites.FavoriteRoute
import com.santimattius.kmp.entertainment.feature.movie.detail.MovieDetailRoute
import com.santimattius.kmp.entertainment.feature.movie.home.MoviesRoute
import com.santimattius.kmp.entertainment.feature.splash.SplashScreen
import com.santimattius.kmp.entertainment.feature.tvshow.detail.TvShowDetailRoute
import com.santimattius.kmp.entertainment.feature.tvshow.home.TvShowRoute
import kotlinx.serialization.Serializable


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    SharedTransitionLayout {
        CompositionLocalProvider(LocalSharedTransitionScope provides this) {
            NavHost(
                navController = navController,
                startDestination = Splash
            ) {
                composableNavAnimated<Splash> {
                    SplashScreen {
                        with(navController) {
                            popBackStack()
                            navigate(Movie)
                        }
                    }
                }
                composableNavAnimated<Movie> {
                    MoviesRoute {
                        navController.navigate(MovieDetail(it))
                    }
                }
                composableNavAnimated<MovieDetail> { backStackEntry ->
                    val detail = backStackEntry.toRoute<MovieDetail>()
                    MovieDetailRoute(detail.id)
                }
                composableNavAnimated<TvShow> {
                    TvShowRoute {
                        navController.navigate(TvShowDetail(it))
                    }
                }
                composableNavAnimated<TvShowDetail> { backStackEntry ->
                    val detail = backStackEntry.toRoute<TvShowDetail>()
                    TvShowDetailRoute(detail.id)
                }
                composableNavAnimated<Favorite> {
                    FavoriteRoute {
                        when (it.type) {
                            ContentType.MOVIE -> {
                                navController.navigate(MovieDetail(it.id))
                            }

                            ContentType.TV -> {
                                navController.navigate(TvShowDetail(it.id))
                            }
                        }
                    }
                }
            }
        }
    }
}

// TODO: move this definitions
inline fun <reified T : Any> NavGraphBuilder.composableNavAnimated(
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable<T> {
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides this@composable
        ) {
            content(it)
        }
    }
}

// TODO: move this definitions

@Serializable
data object Splash

@Serializable
data object Movie

@Serializable
data class MovieDetail(val id: Long)

@Serializable
data object TvShow

@Serializable
data class TvShowDetail(val id: Long)

@Serializable
data object Favorite
