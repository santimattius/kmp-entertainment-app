package com.santimattius.kmp.entertainment.feature.movie.detail

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.santimattius.kmp.entertainment.core.extensions.koinViewModel
import com.santimattius.kmp.entertainment.core.ui.components.Center
import com.santimattius.kmp.entertainment.feature.shared.DetailContentView
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import org.koin.core.parameter.parametersOf

@Composable
fun MovieDetailRoute(id: Long) {
    val viewModel = koinViewModel(MovieDetailViewModel::class) { parametersOf(id) }
    MovieDetailContent(viewModel)
}

@Composable
fun MovieDetailContent(
    viewModel: MovieDetailViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DetailContent(state)
}

@Composable
fun DetailContent(state: MovieDetailUiState) {
    when {
        state.isLoading -> {
            Center {
                CircularProgressIndicator()
            }
        }

        state.data == null || state.isFailure -> {
            Center {
                Text("Ha ocurrido un error")
            }
        }

        else -> {
            DetailContentView(
                imageUrl = state.data.image,
                title = state.data.title,
                overview = state.data.overview
            )
        }
    }
}

