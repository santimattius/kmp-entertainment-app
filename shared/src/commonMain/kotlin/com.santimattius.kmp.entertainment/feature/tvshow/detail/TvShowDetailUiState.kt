package com.santimattius.kmp.entertainment.feature.tvshow.detail

data class TvShowDetailUiState(
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val data: TvShowUiModel? = null,
)

data class TvShowUiModel(
    val id: Long,
    val title: String,
    val image: String,
    val overview: String,
    val isFavorite: Boolean = false,
)
