package com.santimattius.kmp.entertainment.feature.movie.detail

data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val data: MovieUiModel? = null,
)


data class MovieUiModel(
    val id: Long,
    val title: String,
    val image: String,
    val overview: String,
    val isFavorite: Boolean = false,
)