package com.santimattius.kmp.entertainment.feature.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.santimattius.kmp.entertainment.core.ui.components.AppBar
import com.santimattius.kmp.entertainment.core.ui.components.Center
import com.santimattius.kmp.entertainment.core.ui.components.DraggableGrid
import com.santimattius.kmp.entertainment.core.ui.components.NetworkImage
import com.santimattius.kmp.entertainment.di.ServiceLocator
import com.santimattius.kmp.entertainment.feature.detail.DetailScreen


object HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel =
            rememberScreenModel { ServiceLocator.provideHomeViewModel() }
        Home(
            viewModel = viewModel,
            onMovieClick = { id ->
                navigator.push(DetailScreen(id))
            }
        )
    }
}

@Composable
fun Home(
    viewModel: HomeViewModel,
    onMovieClick: (Long) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = { AppBar("Movies") },
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            HomeContent(
                state = state,
                onMovieClick = onMovieClick,
                onMove = viewModel::move
            )
        }
    }
}

@Composable
fun HomeContent(
    state: HomeUiState,
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
            MoviesContent(
                data = movies,
                onItemMove = onMove,
                onItemClick = onMovieClick,
            )
        }
    }
}

@Composable
fun MoviesContent(
    data: List<MovieUiModel>,
    onItemMove: (Int, Int) -> Unit,
    onItemClick: (Long) -> Unit = {},
) {
    DraggableGrid(items = data, onMove = onItemMove) { item, isDragging ->
        val elevation by animateDpAsState(if (isDragging) 4.dp else 1.dp)
        CardGridItem(
            modifier = Modifier.clickable { onItemClick(item.id) },
            item = item,
            elevation = elevation
        )
    }
}

private const val IMAGE_ASPECT_RATIO = 0.67f

@Composable
fun CardGridItem(
    modifier: Modifier = Modifier,
    item: MovieUiModel,
    elevation: Dp,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = elevation)
    ) {
        NetworkImage(
            imageUrl = item.image,
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(ratio = IMAGE_ASPECT_RATIO),
        )
    }
}