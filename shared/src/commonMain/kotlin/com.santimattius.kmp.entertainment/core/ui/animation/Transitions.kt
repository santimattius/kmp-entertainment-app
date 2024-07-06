package com.santimattius.kmp.entertainment.core.ui.animation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }

val ProvidableCompositionLocal<AnimatedVisibilityScope?>.currentOrThrow: AnimatedVisibilityScope
    @Composable
    get() = current ?: throw IllegalStateException("No animatedVisibilityScope found")

@OptIn(ExperimentalSharedTransitionApi::class)
val ProvidableCompositionLocal<SharedTransitionScope?>.currentOrThrow: SharedTransitionScope
    @Composable
    get() = current ?: throw IllegalStateException("No sharedTransitionScope found")