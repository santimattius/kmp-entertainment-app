package com.santimattius.kmp.entertainment.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.santimattius.kmp.entertainment.core.ui.animation.LocalNavAnimatedVisibilityScope
import com.santimattius.kmp.entertainment.core.ui.animation.LocalSharedTransitionScope

//TODO: test this with nav3
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

//TODO: test this with nav3
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