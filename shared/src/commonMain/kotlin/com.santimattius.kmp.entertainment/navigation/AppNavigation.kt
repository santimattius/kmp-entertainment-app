package com.santimattius.kmp.entertainment.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.santimattius.kmp.entertainment.core.domain.ContentType
import com.santimattius.kmp.entertainment.core.ui.animation.LocalSharedTransitionScope
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
