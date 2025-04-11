package com.santimattius.kmp.entertainment.feature.shared

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.santimattius.kmp.entertainment.core.ui.animation.LocalNavAnimatedVisibilityScope
import com.santimattius.kmp.entertainment.core.ui.animation.LocalSharedTransitionScope
import com.santimattius.kmp.entertainment.core.ui.animation.EntertainmentSharedElementKey
import com.santimattius.kmp.entertainment.core.ui.animation.EntertainmentSharedElementType
import com.santimattius.kmp.entertainment.core.ui.animation.currentOrThrow
import com.santimattius.kmp.entertainment.core.ui.animation.detailBoundsTransform
import com.santimattius.kmp.entertainment.core.ui.components.NetworkImage

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailContentView(
    model: UiItem,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val sharedTransitionScope = LocalSharedTransitionScope.currentOrThrow
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.currentOrThrow
    with(sharedTransitionScope) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Card(
                modifier = Modifier.padding(8.dp).sharedBounds(
                    rememberSharedContentState(
                        key = EntertainmentSharedElementKey(
                            id = model.id,
                            origin = "",
                            type = EntertainmentSharedElementType.Image
                        )
                    ),
                    animatedVisibilityScope = animatedVisibilityScope,
                    exit = fadeOut(),
                    enter = fadeIn(),
                    boundsTransform = detailBoundsTransform
                )
            ) {
                NetworkImage(
                    image = model.imageUrl,
                    contentDescription = model.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.6f)
                        .background(Color.LightGray, shape = MaterialTheme.shapes.large)
                        .aspectRatio(ratio = 0.67f)
                )
            }
            Row(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                )
            ) {
                Text(
                    modifier = Modifier.weight(2f).fillMaxWidth()
                        .sharedBounds(
                            rememberSharedContentState(
                                key = EntertainmentSharedElementKey(
                                    id = model.id,
                                    origin = "",
                                    type = EntertainmentSharedElementType.Title
                                )
                            ),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = detailBoundsTransform
                        ),
                    text = model.title,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                )

                SmallFloatingActionButton(
                    onClick = onFavoriteClick
                ) {
                    if (isFavorite) {
                        Icon(Icons.Default.Favorite, contentDescription = "")
                    } else {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = "")
                    }
                }
            }
            Text(
                text = model.description,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.sharedBounds(
                    rememberSharedContentState(
                        key = EntertainmentSharedElementKey(
                            id = model.id,
                            origin = "",
                            type = EntertainmentSharedElementType.Overview
                        )
                    ),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = detailBoundsTransform
                )
                    .fillMaxWidth()
                    .padding(
                        horizontal = 8.dp,
                        vertical = 8.dp
                    )
            )
        }
    }

}
