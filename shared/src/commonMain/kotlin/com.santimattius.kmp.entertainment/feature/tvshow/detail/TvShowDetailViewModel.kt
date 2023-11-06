package com.santimattius.kmp.entertainment.feature.tvshow.detail

import com.santimattius.kmp.entertainment.core.data.TvShowRepository
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
            _state.update { it.copy(isLoading = false, data = tvShow) }
        }
    }
}