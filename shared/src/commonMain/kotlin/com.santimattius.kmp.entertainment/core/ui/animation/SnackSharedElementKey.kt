package com.santimattius.kmp.entertainment.core.ui.animation

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.spring

data class SnackSharedElementKey(
    val snackId: Long,
    val origin: String,
    val type: SnackSharedElementType
)

enum class SnackSharedElementType {
    Image,
    Title,
    Overview
}

@OptIn(ExperimentalSharedTransitionApi::class)
val snackDetailBoundsTransform = BoundsTransform { _, _ ->
    spatialExpressiveSpring()
}
fun <T> spatialExpressiveSpring() = spring<T>(
    dampingRatio = 0.8f,
    stiffness = 380f
)