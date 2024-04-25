package com.santimattius.kmp.entertainment.feature.tvshow.detail

import com.santimattius.kmp.entertainment.core.data.repositories.FavoriteRepository
import com.santimattius.kmp.entertainment.core.data.repositories.TvShowRepository
import com.santimattius.kmp.entertainment.core.domain.ContentType
import com.santimattius.kmp.entertainment.core.domain.Favorite
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class TvShowDetailViewModel(
    id: Long,
    private val repository: TvShowRepository,
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(TvShowDetailUiState(isLoading = true))
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
            val tvShow = repository.findById(id)
            val isFavorite = favoriteRepository.isFavorite(tvShow.id)
            val tvShowUiModel = TvShowUiModel(
                id = tvShow.id,
                title = tvShow.title,
                overview = tvShow.overview,
                image = tvShow.image,
                isFavorite = isFavorite
            )
            _state.update { it.copy(isLoading = false, data = tvShowUiModel) }
        }
    }

    fun onFavoriteClicked(tvShow: TvShowUiModel) {
        if (tvShow.isFavorite) {
            favoriteRepository.remove(tvShow.id)
        } else {
            val favorite = Favorite(
                resourceId = tvShow.id,
                title = tvShow.title,
                overview = tvShow.overview,
                imageUrl = tvShow.image,
                type = ContentType.TV,
            )
            favoriteRepository.add(favorite)
        }
        _state.update {
            it.copy(
                isLoading = false,
                data = tvShow.copy(isFavorite = !tvShow.isFavorite)
            )
        }
    }
}