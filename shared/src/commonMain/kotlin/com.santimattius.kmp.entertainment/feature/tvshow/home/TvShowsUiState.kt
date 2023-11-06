package com.santimattius.kmp.entertainment.feature.tvshow.home

data class TvShowsUiState(
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val data: List<TvShowUiModel> = emptyList(),
)