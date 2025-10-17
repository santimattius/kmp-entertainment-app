package com.santimattius.kmp.entertainment.feature.tvshow.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.santimattius.kmp.entertainment.core.data.repositories.FavoriteRepository
import com.santimattius.kmp.entertainment.core.data.repositories.TvShowRepository
import com.santimattius.kmp.entertainment.core.domain.ContentType
import com.santimattius.kmp.entertainment.core.domain.Favorite
import com.santimattius.kmp.entertainment.navigation.TvShowDetail
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue
import kotlin.reflect.KClass

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
        viewModelScope.launch(exceptionHandler) {
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

    class Factory(
        private val key: TvShowDetail,
    ) : ViewModelProvider.Factory, KoinComponent {
        private val tvShowRepository: TvShowRepository by inject()
        private val favoriteRepository: FavoriteRepository by inject()

        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
            return TvShowDetailViewModel(key.id, tvShowRepository, favoriteRepository) as T
        }
    }
}