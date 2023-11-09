package com.santimattius.kmp.entertainment.feature.tvshow.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.santimattius.kmp.entertainment.core.ui.components.Center
import com.santimattius.kmp.entertainment.core.ui.components.DraggableGrid
import com.santimattius.kmp.entertainment.feature.shared.ContentImageView
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel


@Composable
fun TvShowRoute(
    onMovieClick: (Long) -> Unit,
) {
    val viewModel = koinViewModel(TvShowViewModel::class)
    TvShowScreen(viewModel, onMovieClick)
}

@Composable
fun TvShowScreen(
    viewModel: TvShowViewModel,
    onMovieClick: (Long) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TvShowContent(
        state = state,
        onMovieClick = onMovieClick,
        onMove = viewModel::move
    )
}

@Composable
fun TvShowContent(
    state: TvShowsUiState,
    onMovieClick: (Long) -> Unit = {},
    onMove: (Int, Int) -> Unit,
) {
    when {
        state.isLoading -> {
            Center {
                CircularProgressIndicator()
            }
        }

        state.isFailure -> {
            Center {
                Text("Ha ocurrido un error")
            }
        }

        else -> {
            val movies = state.data.toMutableStateList()
            TvShowContent(
                data = movies,
                onItemMove = onMove,
                onItemClick = onMovieClick,
            )
        }
    }
}

@Composable
fun TvShowContent(
    data: List<TvShowUiModel>,
    onItemMove: (Int, Int) -> Unit,
    onItemClick: (Long) -> Unit = {},
) {
    DraggableGrid(items = data, onMove = onItemMove) { item, isDragging ->
        val elevation by animateDpAsState(if (isDragging) 4.dp else 1.dp)
        ContentImageView(
            modifier = Modifier.clickable { onItemClick(item.id) },
            imageUrl = item.image,
            imageDescription = item.title,
            elevation = elevation
        )
    }
}
