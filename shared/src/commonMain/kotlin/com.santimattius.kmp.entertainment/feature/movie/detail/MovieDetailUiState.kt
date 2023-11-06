package com.santimattius.kmp.entertainment.feature.movie.detail

import com.santimattius.kmp.entertainment.core.domain.Movie

data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val data: Movie? = null,
)