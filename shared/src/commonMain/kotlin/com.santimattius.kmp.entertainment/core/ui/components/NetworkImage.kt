package com.santimattius.kmp.entertainment.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp

@Composable
internal fun NetworkImage(
    image: Any,
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
    contentDescription: String? = null,
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(image).build(),
        loading = {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}