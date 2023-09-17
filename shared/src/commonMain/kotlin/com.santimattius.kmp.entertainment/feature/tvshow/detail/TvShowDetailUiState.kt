package com.santimattius.kmp.entertainment.feature.tvshow.detail

import com.santimattius.kmp.entertainment.core.domain.TvShow

data class TvShowDetailUiState(
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val data: TvShow? = null,
)