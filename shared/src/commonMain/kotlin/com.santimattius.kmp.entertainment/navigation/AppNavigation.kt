package com.santimattius.kmp.entertainment.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.parseStringAsNavUri
import androidx.navigation.toRoute
import com.santimattius.kmp.entertainment.core.domain.ContentType
import com.santimattius.kmp.entertainment.feature.favorites.FavoriteRoute
import com.santimattius.kmp.entertainment.feature.movie.detail.MovieDetailRoute
import com.santimattius.kmp.entertainment.feature.movie.home.MoviesRoute
import com.santimattius.kmp.entertainment.feature.splash.SplashScreen
import com.santimattius.kmp.entertainment.feature.tvshow.detail.TvShowDetailRoute
import com.santimattius.kmp.entertainment.feature.tvshow.home.TvShowRoute


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    DisposableEffect(Unit) {
        // Sets up the listener to call `NavController.navigate()`
        // for the composable that has a matching `navDeepLink` listed
        ExternalUriHandler.listener = { uri ->
            navController.navigate(parseStringAsNavUri(uri))
        }
        // Removes the listener when the composable is no longer active
        onDispose {
            ExternalUriHandler.listener = null
        }
    }
    AnimatedTransactionLayout {
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
            //"app://entertainment.com/movie/1197306"
            composableNavAnimated<MovieDetail>(
                deepLinks = listOf(
                    navDeepLink<MovieDetail>(basePath = "miapp://entertainment.com/movie"),
                    //navDeepLink { uriPattern = "miapp://entertainment.com/movie/{id}" }
                )
            ) { backStackEntry ->
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
