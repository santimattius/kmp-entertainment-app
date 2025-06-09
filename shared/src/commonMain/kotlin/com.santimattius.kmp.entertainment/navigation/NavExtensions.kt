package com.santimattius.kmp.entertainment.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MovableContent
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.santimattius.kmp.entertainment.core.ui.animation.LocalNavAnimatedVisibilityScope
import com.santimattius.kmp.entertainment.core.ui.animation.LocalSharedTransitionScope

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
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable<T>(
        deepLinks = deepLinks
    ) {
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides this@composable
        ) {
            content(it)
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AnimatedTransactionLayout(content: @Composable () -> Unit) {
    SharedTransitionLayout {
        CompositionLocalProvider(
            value = LocalSharedTransitionScope provides this,
            content = content
        )
    }
}