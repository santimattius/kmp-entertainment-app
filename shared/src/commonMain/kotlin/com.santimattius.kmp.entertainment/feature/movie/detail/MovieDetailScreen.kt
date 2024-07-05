package com.santimattius.kmp.entertainment.feature.movie.detail

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.santimattius.kmp.entertainment.core.extensions.koinViewModel
import com.santimattius.kmp.entertainment.core.ui.components.Center
import com.santimattius.kmp.entertainment.feature.shared.DetailContentView
import org.koin.core.parameter.parametersOf

@Composable
fun MovieDetailRoute(id: Long) {
    val viewModel = koinViewModel<MovieDetailViewModel> { parametersOf(id) }
    MovieDetailContent(viewModel)
}

@Composable
fun MovieDetailContent(
    viewModel: MovieDetailViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DetailContent(state = state, onFavoriteClicked = viewModel::onFavoriteClicked)
}

@Composable
fun DetailContent(
    state: MovieDetailUiState,
    onFavoriteClicked: (MovieUiModel) -> Unit = {},
) {
    val content: MovieUiModel? = state.data

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
                onFavoriteClick = { onFavoriteClicked(content) }
            )
        }
    }
}

