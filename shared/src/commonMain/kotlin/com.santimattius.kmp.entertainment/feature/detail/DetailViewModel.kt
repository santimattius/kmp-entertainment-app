package com.santimattius.kmp.entertainment.feature.detail

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.santimattius.kmp.entertainment.core.data.MovieRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val id: Long,
    private val repository: MovieRepository,
) : ScreenModel {

    private val _state = MutableStateFlow(DetailUiState(isLoading = true))
    val state = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.update { it.copy(isLoading = false, isFailure = true) }
    }

    init {
        find(id)
    }

    private fun find(id: Long) {
        coroutineScope.launch(exceptionHandler) {
            _state.update { it.copy(isLoading = true) }
            val movie = repository.findById(id)
            _state.update { it.copy(isLoading = false, data = movie) }
        }
    }
}