package com.santimattius.kmp.entertainment.feature.movie.detail

import com.santimattius.kmp.entertainment.core.data.repositories.MovieRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class MovieDetailViewModel(
    id: Long,
    private val repository: MovieRepository,
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
            val movie = repository.findById(id)
            _state.update { it.copy(isLoading = false, data = movie) }
        }
    }
}