package com.santimattius.kmp.entertainment.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.santimattius.kmp.entertainment.core.ui.animation.LocalNavAnimatedVisibilityScope

fun <T : Any> NavHostController.navigatePoppingUpToStartDestination(route: T) {
    navigate(route) {
        popUpTo(graph.findStartDestination().navigatorName) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

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