package com.santimattius.kmp.entertainment.feature.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val data: List<MovieUiModel> = emptyList(),
)