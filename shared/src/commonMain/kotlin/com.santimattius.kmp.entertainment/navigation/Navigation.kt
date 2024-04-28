package com.santimattius.kmp.entertainment.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.santimattius.kmp.entertainment.core.domain.ContentType
import com.santimattius.kmp.entertainment.feature.favorites.FavoriteRoute
import com.santimattius.kmp.entertainment.feature.movie.detail.MovieDetailRoute
import com.santimattius.kmp.entertainment.feature.movie.home.MoviesRoute
import com.santimattius.kmp.entertainment.feature.splash.SplashScreen
import com.santimattius.kmp.entertainment.feature.tvshow.detail.TvShowDetailRoute
import com.santimattius.kmp.entertainment.feature.tvshow.home.TvShowRoute

private const val TWEEN_ANIM_DURATION = 1000

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
) {
    BoxWithConstraints {
        NavHost(
            navController = navController,
            startDestination = Features.Splash.route
        ) {
            splashNav(
                navController = navController,
            )
            movieNav(
                navController = navController,
                width = constraints.maxWidth,
            )
            tvShowNav(
                navController = navController,
                width = constraints.maxWidth,
            )
            favoriteNav(navController = navController)
        }
    }
}

private fun NavGraphBuilder.splashNav(
    navController: NavController,
) {
    navigation(
        startDestination = NavCommand.ContentType(Features.Splash).route,
        route = Features.Splash.route
    ) {
        composable(
            navCommand = NavCommand.ContentType(Features.Splash)
        ) {
            SplashScreen {
                with(navController) {
                    popBackStack()
                    navigate(Features.Movies.route)
                }
            }
        }
    }
}

private fun NavGraphBuilder.movieNav(
    navController: NavController,
    width: Int,
 ) {
    navigation(
        startDestination = NavCommand.ContentType(Features.Movies).route,
        route = Features.Movies.route
    ) {
        composable(
            navCommand = NavCommand.ContentType(Features.Movies),
        ) {
            MoviesRoute {
                navController.navigate(
                    NavCommand.ContentTypeDetail(Features.Movies).createRoute(it)
                )
            }
        }
        composable(
            navCommand = NavCommand.ContentTypeDetail(Features.Movies),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { width }
                ) + fadeIn(animationSpec = tween(TWEEN_ANIM_DURATION))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { width }
                ) + fadeOut(animationSpec = tween(TWEEN_ANIM_DURATION))
            }
        ) { backStackEntry ->
            val id: Long =
                backStackEntry.arguments?.getLong("id") ?: throw IllegalArgumentException()
            MovieDetailRoute(id)
        }
    }
}

private fun NavGraphBuilder.tvShowNav(
    navController: NavController,
    width: Int,
) {
    navigation(
        startDestination = NavCommand.ContentType(Features.TvShows).route,
        route = Features.TvShows.route
    ) {
        composable(
            navCommand = NavCommand.ContentType(Features.TvShows),
        ) {
            TvShowRoute {
                navController.navigate(
                    NavCommand.ContentTypeDetail(Features.TvShows).createRoute(it)
                )
            }
        }
        composable(
            navCommand = NavCommand.ContentTypeDetail(Features.TvShows),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { width }
                )
            }
        ) { backStackEntry ->
            val id: Long =
                backStackEntry.arguments?.getLong("id") ?: throw IllegalArgumentException()
            TvShowDetailRoute(id)
        }
    }
}

private fun NavGraphBuilder.favoriteNav(
    navController: NavController,
) {
    navigation(
        startDestination = NavCommand.ContentType(Features.Favorites).route,
        route = Features.Favorites.route
    ) {
        composable(navCommand = NavCommand.ContentType(Features.Favorites)) {
            FavoriteRoute {
                val content = when (it.type) {
                    ContentType.MOVIE -> Features.Movies
                    ContentType.TV -> Features.TvShows
                }
                navController.navigate(NavCommand.ContentTypeDetail(content).createRoute(it.id))
            }
        }
    }
}