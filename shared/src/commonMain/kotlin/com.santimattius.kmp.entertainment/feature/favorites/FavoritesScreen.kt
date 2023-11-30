package com.santimattius.kmp.entertainment.feature.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.santimattius.kmp.entertainment.core.extensions.koinViewModel
import com.santimattius.kmp.entertainment.feature.shared.ContentItemView
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle


@Composable
fun FavoriteRoute(
    onFavoriteClick: (Long) -> Unit,
) {
    val viewModel = koinViewModel(FavoriteViewModel::class)
    FavoriteScreen(viewModel, onFavoriteClick)
}

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    onFavoriteClick: (Long) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FavoriteContent(
        data = state,
        onItemClick = onFavoriteClick,
    )
}

@Composable
fun FavoriteContent(
    data: List<FavoriteUiModel>,
    onItemClick: (Long) -> Unit = {},
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = data, key = { item -> item.id }) { item ->
            ContentItemView(
                modifier = Modifier.clickable { onItemClick(item.id) },
                item = item,
            )
        }
    }
}
