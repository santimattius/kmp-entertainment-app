package com.santimattius.kmp.entertainment.feature.detail

import com.santimattius.kmp.entertainment.core.domain.Movie

data class DetailUiState(
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val data: Movie? = null,
)