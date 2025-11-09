package com.santimattius.kmp.entertainment.feature.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.santimattius.kmp.entertainment.core.domain.ContentType
import com.santimattius.kmp.entertainment.core.extensions.koinViewModel
import com.santimattius.kmp.entertainment.core.ui.components.Center
import com.santimattius.kmp.entertainment.core.ui.components.CustomAnimatedVisibility
import com.santimattius.kmp.entertainment.core.ui.components.SwipeToDismissComponent
import com.santimattius.kmp.entertainment.core.ui.themes.AppContainer
import com.santimattius.kmp.entertainment.feature.shared.ContentItemView
import org.koin.core.annotation.KoinInternalApi


@OptIn(KoinInternalApi::class)
@Composable
fun FavoriteRoute(
    onFavoriteClick: (FavoriteUiModel) -> Unit,
) {
    val viewModel = koinViewModel<FavoriteViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    FavoriteScreen(
        state = state,
        onFavoriteClick = onFavoriteClick,
        onFavoriteDelete = viewModel::onFavoriteDelete
    )
}

@Composable
fun FavoriteScreen(
    state: List<FavoriteUiModel>,
    onFavoriteClick: (FavoriteUiModel) -> Unit = {},
    onFavoriteDelete: (FavoriteUiModel) -> Unit = {},
) {

    if (state.isEmpty()) {
        Center {
            Text(
                text = "There is no favorite content",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    } else {
        FavoriteContent(
            data = state.toMutableStateList(),
            onItemClick = onFavoriteClick,
            onItemDelete = onFavoriteDelete
        )
    }
}

@Composable
fun FavoriteContent(
    data: List<FavoriteUiModel>,
    onItemClick: (FavoriteUiModel) -> Unit = {},
    onItemDelete: (FavoriteUiModel) -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = data, key = { item -> item.id }) { item ->
            FavoriteItem(
                item = item,
                onItemClick = onItemClick,
                onItemDelete = onItemDelete
            )
        }
    }
}

@Composable
private fun FavoriteItem(
    item: FavoriteUiModel,
    onItemClick: (FavoriteUiModel) -> Unit,
    onItemDelete: (FavoriteUiModel) -> Unit,
) {
    val dismissState: SwipeToDismissBoxState = rememberSwipeToDismissBoxState()
    val isDismissed = dismissState.currentValue == SwipeToDismissBoxValue.EndToStart
    if (isDismissed) {
        onItemDelete(item)
    }
    CustomAnimatedVisibility(visible = isDismissed) {
        SwipeToDismissComponent(dismissState = dismissState) {
            ContentItemView(
                modifier = Modifier.clickable { onItemClick(item) },
                item = item,
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
fun FavoriteScreenPreview() {
    val previewHandler = AsyncImagePreviewHandler {
        ColorImage(Color.Red.toArgb())
    }
    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        AppContainer {
            FavoriteScreen(
                state = (1..10).map { id ->
                    FavoriteUiModel(
                        id = id.toLong(),
                        title = "title_$id",
                        description = "description_$id",
                        imageUrl = "imageUrl_$id",
                        type = ContentType.MOVIE
                    )
                }
            )
        }
    }

}