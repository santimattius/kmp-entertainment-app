package com.santimattius.kmp.entertainment.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
internal expect fun __NetworkImage(
    imageUrl: String,
    modifier: Modifier,
    contentScale: ContentScale,
    contentDescription: String?,
)

@Composable
internal fun NetworkImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
    contentDescription: String? = null,
) {
    __NetworkImage(
        imageUrl = imageUrl,
        modifier = modifier,
        contentScale = contentScale,
        contentDescription = contentDescription
    )
}