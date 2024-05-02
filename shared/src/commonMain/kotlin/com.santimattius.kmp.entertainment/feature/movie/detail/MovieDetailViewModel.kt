package com.santimattius.kmp.entertainment.feature.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santimattius.kmp.entertainment.core.data.repositories.FavoriteRepository
import com.santimattius.kmp.entertainment.core.data.repositories.MovieRepository
import com.santimattius.kmp.entertainment.core.domain.ContentType
import com.santimattius.kmp.entertainment.core.domain.Favorite
import com.santimattius.kmp.entertainment.core.domain.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    id: Long,
    private val movieRepository: MovieRepository,
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailUiState(isLoading = true))
    val state = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.update { it.copy(isLoading = false, isFailure = true) }
    }

    init {
        find(id)
    }

    private fun find(id: Long) {
        viewModelScope.launch(exceptionHandler) {
            _state.update { it.copy(isLoading = true) }
            val movie = movieRepository.findById(id).asUiModel()
            val isFavorite = favoriteRepository.isFavorite(movie.id)
            _state.update { it.copy(isLoading = false, data = movie.copy(isFavorite = isFavorite)) }
        }
    }

    fun onFavoriteClicked(movie: MovieUiModel) {
        if (movie.isFavorite) {
            favoriteRepository.remove(movie.id)
        } else {
            val favorite = Favorite(
                resourceId = movie.id,
                title = movie.title,
                overview = movie.overview,
                imageUrl = movie.image,
                type = ContentType.MOVIE,
            )
            favoriteRepository.add(favorite)
        }
        _state.update {
            it.copy(
                isLoading = false,
                data = movie.copy(isFavorite = !movie.isFavorite)
            )
        }
    }
}

private fun Movie.asUiModel(): MovieUiModel {
    return MovieUiModel(
        id = id, title = title, image = image, overview = overview
    )
}
