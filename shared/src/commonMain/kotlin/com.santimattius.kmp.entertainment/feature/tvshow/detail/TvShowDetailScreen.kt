package com.santimattius.kmp.entertainment.feature.tvshow.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.santimattius.kmp.entertainment.core.ui.components.Center
import com.santimattius.kmp.entertainment.feature.shared.DetailContentView

@Composable
fun TvShowDetailScene(
    viewModel: TvShowDetailViewModel,
){
    TvShowDetailContent(viewModel)
}

@Composable
fun TvShowDetailContent(
    viewModel: TvShowDetailViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)){
        DetailContent(state = state, onFavoriteClicked = viewModel::onFavoriteClicked)
    }
}

@Composable
fun DetailContent(
    state: TvShowDetailUiState,
    onFavoriteClicked: (TvShowUiModel) -> Unit = {},
) {
    val content: TvShowUiModel? = state.data
    when {
        state.isLoading -> {
            Center {
                CircularProgressIndicator()
            }
        }

        content == null || state.isFailure -> {
            Center {
                Text("Ha ocurrido un error")
            }
        }

        else -> {
            DetailContentView(
                model = content,
                isFavorite = content.isFavorite,
                onFavoriteClick = {
                    onFavoriteClicked(content)
                }
            )
        }
    }
}

