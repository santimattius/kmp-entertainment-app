package com.santimattius.kmp.entertainment.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable

@Composable
fun CustomAnimatedVisibility(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    AnimatedVisibility(
        visible = !visible,
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = 300,
            )
        ),
        enter = expandVertically(
            animationSpec = tween(
                durationMillis = 300
            )
        ),
        content = content
    )
}