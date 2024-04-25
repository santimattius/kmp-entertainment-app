package com.santimattius.kmp.entertainment.feature.tvshow.home

import com.santimattius.kmp.entertainment.core.data.repositories.TvShowRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class TvShowViewModel(
    private val repository: TvShowRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(TvShowsUiState(isLoading = true))
    val state = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.update { it.copy(isLoading = false, isFailure = true) }
    }

    init {
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch(exceptionHandler) {
            _state.update { it.copy(isLoading = true) }
            val movies = repository.getTvShows().map {
                TvShowUiModel(
                    id = it.id,
                    title = it.title,
                    image = it.image
                )
            }
            _state.update { it.copy(isLoading = false, data = movies) }
        }
    }

    fun move(fromIndex: Int, toIndex: Int) {
        val movies = _state.value.data.toMutableList()
        movies.add(toIndex, movies.removeAt(fromIndex))
        _state.update { it.copy(data = movies) }
    }
}