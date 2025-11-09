package com.santimattius.kmp.entertainment.feature.movie.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.santimattius.kmp.entertainment.core.extensions.koinViewModel
import com.santimattius.kmp.entertainment.core.ui.components.Center
import com.santimattius.kmp.entertainment.core.ui.themes.AppContainer
import com.santimattius.kmp.entertainment.feature.shared.DetailContentView
import org.koin.core.parameter.parametersOf

@Composable
@Deprecated("Use MovieDetailScene instead for Nav3")
fun MovieDetailRoute(id: Long, navigateToWebPage: (String) -> Unit = {}) {
    val viewModel = koinViewModel<MovieDetailViewModel> { parametersOf(id) }
    MovieDetailContent(viewModel, navigateToWebPage)
}

@Composable
fun MovieDetailScene(
    viewModel: MovieDetailViewModel,
    navigateToWebPage: (String) -> Unit = {}
){
    MovieDetailContent(viewModel, navigateToWebPage)
}

@Composable
private fun MovieDetailContent(
    viewModel: MovieDetailViewModel,
    navigateToWebPage: (String) -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)){
        DetailContent(
            state = state,
            onFavoriteClicked = viewModel::onFavoriteClicked,
            navigateToWebPage = navigateToWebPage
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(
    state: MovieDetailUiState,
    onFavoriteClicked: (MovieUiModel) -> Unit = {},
    navigateToWebPage: (String) -> Unit = {}
) {
    val content: MovieUiModel? = state.data
    var openHomePage by rememberSaveable { mutableStateOf<String?>(null) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
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
                showPlayButton = !content.homepage.isNullOrBlank(),
                onFavoriteClick = { onFavoriteClicked(content) },
                onPlayClick = {
                    content.homepage?.let {
                        navigateToWebPage(it)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun MovieDetailScreenPreview() {
    AppContainer {
        DetailContent(
            state = MovieDetailUiState(
                data = MovieUiModel(
                    id = 1,
                    title = "The Galactic Quest",
                    image = "",
                    overview = "This is a captivating movie with a compelling plot and stunning visuals. The characters are well-developed, and the storyline keeps you engaged from beginning to end. A must-watch for movie enthusiasts!",
                    isFavorite = true
                )
            ),
        )
    }
}

