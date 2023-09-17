package com.santimattius.kmp.entertainment.feature.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import com.santimattius.kmp.entertainment.core.ui.components.NetworkImage

private const val IMAGE_ASPECT_RATIO = 0.67f

@Composable
fun ContentImageView(
    modifier: Modifier = Modifier,
    imageUrl: String,
    imageDescription: String,
    elevation: Dp,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = elevation)
    ) {
        NetworkImage(
            imageUrl = imageUrl,
            contentDescription = imageDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(ratio = IMAGE_ASPECT_RATIO),
        )
    }
}