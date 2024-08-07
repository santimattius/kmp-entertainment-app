package com.santimattius.kmp.entertainment.feature.favorites

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.santimattius.kmp.entertainment.core.extensions.koinViewModel
import com.santimattius.kmp.entertainment.core.ui.components.Center
import com.santimattius.kmp.entertainment.core.ui.components.CustomAnimatedVisibility
import com.santimattius.kmp.entertainment.core.ui.components.SwipeToDismissComponent
import com.santimattius.kmp.entertainment.feature.shared.ContentItemView
import org.koin.compose.KoinIsolatedContext
import org.koin.compose.LocalKoinApplication


@Composable
fun FavoriteRoute(
    onFavoriteClick: (FavoriteUiModel) -> Unit,
) {

    KoinIsolatedContext(FavoriteContext.koinApp) {
        val viewModel = koinViewModel<FavoriteViewModel>()
        FavoriteScreen(viewModel, onFavoriteClick)
    }
}

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    onFavoriteClick: (FavoriteUiModel) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
            onItemDelete = viewModel::onFavoriteDelete
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
        modifier = Modifier.fillMaxSize(),
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
