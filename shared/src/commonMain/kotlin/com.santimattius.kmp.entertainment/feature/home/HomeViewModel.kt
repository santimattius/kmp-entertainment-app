package com.santimattius.kmp.entertainment.feature.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.santimattius.kmp.entertainment.core.data.MovieRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MovieRepository,
) : ScreenModel {

    private val _state = MutableStateFlow(HomeUiState(isLoading = true))
    val state = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.update { it.copy(isLoading = false, isFailure = true) }
    }

    init {
        fetch()
    }

    private fun fetch() {
        coroutineScope.launch(exceptionHandler) {
            _state.update { it.copy(isLoading = true) }
            val movies = repository.getMovies().map {
                MovieUiModel(
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