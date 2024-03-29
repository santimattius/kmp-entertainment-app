package com.santimattius.kmp.entertainment.feature.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.santimattius.kmp.entertainment.core.ui.components.NetworkImage
import com.santimattius.kmp.entertainment.core.ui.components.UiModel

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

@Composable
fun ContentItemView(
    modifier: Modifier = Modifier,
    item: UiItem,
) {
    ListItem(
        modifier = modifier.wrapContentHeight(Alignment.Top),
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.background),
        leadingContent = {
            NetworkImage(
                imageUrl = item.imageUrl,
                contentDescription = item.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .background(Color.LightGray)
                    .aspectRatio(ratio = IMAGE_ASPECT_RATIO),
            )
        },
        headlineContent = {
            Text(item.title)
        },
        supportingContent = {
            Text(item.description, maxLines = 6, overflow = TextOverflow.Ellipsis)
        }
    )
}

interface UiItem : UiModel {

    val title: String
    val description: String
    val imageUrl: String
}