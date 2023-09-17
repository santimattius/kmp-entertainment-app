package com.santimattius.kmp.entertainment.feature.movie.home

data class MovieUiState(
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val data: List<MovieUiModel> = emptyList(),
)