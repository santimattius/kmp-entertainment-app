package com.santimattius.kmp.entertainment.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.seiko.imageloader.rememberImagePainter

@Composable
internal actual fun __NetworkImage(
    imageUrl: String,
    modifier: Modifier,
    contentScale: ContentScale,
    contentDescription: String?,
) {
    val painter = rememberImagePainter(imageUrl)
    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription,
    )
}