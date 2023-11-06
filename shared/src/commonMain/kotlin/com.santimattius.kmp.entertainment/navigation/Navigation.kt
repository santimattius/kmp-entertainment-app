package com.santimattius.kmp.entertainment.navigation

import androidx.compose.runtime.Composable
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
        initialRoute = Features.SPLASH.route,
    ) {
        // Define a scene to the navigation graph
        scene(
            route = Features.SPLASH.route,
            navTransition = NavTransition()
        ) {
            SplashScreen {
                navigator.popBackStack()
                navigator.navigate(NavCommand.ContentType(Features.MOVIES).route)
            }
        }

        scene(
            route = NavCommand.ContentType(Features.MOVIES).route
        ) {
            MoviesRoute {
                navigator.navigate(NavCommand.ContentTypeDetail(Features.MOVIES).createRoute(it))
            }
        }

        scene(
            route = NavCommand.ContentType(Features.TV_SHOWS).route
        ) {
            TvShowRoute {
                navigator.navigate(NavCommand.ContentTypeDetail(Features.TV_SHOWS).createRoute(it))
            }
        }

        scene(
            route = NavCommand.ContentTypeDetail(Features.MOVIES).route
        ) { backStackEntry ->
            val id: Long = backStackEntry.path<Long>("id") ?: throw IllegalArgumentException()
            MovieDetailRoute(id)
        }

        scene(
            route = NavCommand.ContentTypeDetail(Features.TV_SHOWS).route
        ) { backStackEntry ->
            val id: Long = backStackEntry.path<Long>("id") ?: throw IllegalArgumentException()
            TvShowDetailRoute(id)
        }
    }
}
