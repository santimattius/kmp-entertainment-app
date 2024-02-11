package com.santimattius.kmp.entertainment.navigation

import androidx.compose.runtime.Composable
import com.santimattius.kmp.entertainment.core.domain.ContentType
import com.santimattius.kmp.entertainment.feature.favorites.FavoriteRoute
import com.santimattius.kmp.entertainment.feature.movie.detail.MovieDetailRoute
import com.santimattius.kmp.entertainment.feature.movie.home.MoviesRoute
import com.santimattius.kmp.entertainment.feature.splash.SplashScreen
import com.santimattius.kmp.entertainment.feature.tvshow.detail.TvShowDetailRoute
import com.santimattius.kmp.entertainment.feature.tvshow.home.TvShowRoute
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

@Composable
fun Navigation(
    navigator: Navigator = rememberNavigator(),
) {
    NavHost(
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = Features.Splash.route,
    ) {
        // Define a scene to the navigation graph
        scene(
            route = Features.Splash.route,
            navTransition = NavTransition()
        ) {
            SplashScreen {
                navigator.popBackStack()
                navigator.navigate(NavCommand.ContentType(Features.Movies).route)
            }
        }

        scene(
            route = NavCommand.ContentType(Features.Movies).route
        ) {
            MoviesRoute {
                navigator.navigate(NavCommand.ContentTypeDetail(Features.Movies).createRoute(it))
            }
        }

        scene(
            route = NavCommand.ContentType(Features.TvShows).route
        ) {
            TvShowRoute {
                navigator.navigate(NavCommand.ContentTypeDetail(Features.TvShows).createRoute(it))
            }
        }

        scene(
            route = NavCommand.ContentType(Features.Favorites).route
        ) {
            FavoriteRoute {
                val content = when (it.type) {
                    ContentType.MOVIE -> Features.Movies
                    ContentType.TV -> Features.TvShows
                }
                navigator.navigate(NavCommand.ContentTypeDetail(content).createRoute(it.id))
            }
        }

        scene(
            route = NavCommand.ContentTypeDetail(Features.Movies).route
        ) { backStackEntry ->
            val id: Long = backStackEntry.path<Long>("id") ?: throw IllegalArgumentException()
            MovieDetailRoute(id)
        }

        scene(
            route = NavCommand.ContentTypeDetail(Features.TvShows).route
        ) { backStackEntry ->
            val id: Long = backStackEntry.path<Long>("id") ?: throw IllegalArgumentException()
            TvShowDetailRoute(id)
        }
    }
}
