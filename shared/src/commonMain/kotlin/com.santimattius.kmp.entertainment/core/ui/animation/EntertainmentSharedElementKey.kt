package com.santimattius.kmp.entertainment.core.ui.animation

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.spring

data class EntertainmentSharedElementKey(
    val id: Long,
    val origin: String,
    val type: EntertainmentSharedElementType
)

enum class EntertainmentSharedElementType {
    Image,
    Title,
    Overview
}

@OptIn(ExperimentalSharedTransitionApi::class)
val detailBoundsTransform = BoundsTransform { _, _ ->
    spatialExpressiveSpring()
}
fun <T> spatialExpressiveSpring() = spring<T>(
    dampingRatio = 0.8f,
    stiffness = 380f
)