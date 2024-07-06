package com.santimattius.kmp.entertainment.navigation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.santimattius.kmp.entertainment.core.domain.ContentType
import com.santimattius.kmp.entertainment.feature.favorites.FavoriteRoute
import com.santimattius.kmp.entertainment.feature.movie.detail.MovieDetailRoute
import com.santimattius.kmp.entertainment.feature.movie.home.MoviesRoute
import com.santimattius.kmp.entertainment.feature.splash.SplashScreen
import com.santimattius.kmp.entertainment.feature.tvshow.detail.TvShowDetailRoute
import com.santimattius.kmp.entertainment.feature.tvshow.home.TvShowRoute


@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    BoxWithConstraints {
        NavHost(
            navController = navController,
            startDestination = Splash
        ) {
            composable<Splash> {
                SplashScreen {
                    with(navController) {
                        popBackStack()
                        navigate(Movie)
                    }
                }
            }
            composable<Movie> {
                MoviesRoute {
                    navController.navigate(MovieDetail(it))
                }
            }
            composable<MovieDetail> { backStackEntry ->
                val detail = backStackEntry.toRoute<MovieDetail>()
                MovieDetailRoute(detail.id)
            }
            composable<TvShow> {
                TvShowRoute {
                    navController.navigate(TvShowDetail(it))
                }
            }
            composable<TvShowDetail> { backStackEntry ->
                val detail = backStackEntry.toRoute<TvShowDetail>()
                TvShowDetailRoute(detail.id)
            }
            composable<Favorite> {
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