package com.santimattius.kmp.entertainment.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

typealias AnimatedEnterTransition = AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
typealias AnimatedExitTransition = AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?

fun NavHostController.navigatePoppingUpToStartDestination(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().navigatorName) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

internal fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    enterTransition: AnimatedEnterTransition? = null,
    exitTransition: AnimatedExitTransition? = null,
    popEnterTransition: AnimatedEnterTransition? = enterTransition,
    popExitTransition: AnimatedExitTransition? = exitTransition,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        content(it)
    }
}