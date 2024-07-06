package com.santimattius.kmp.entertainment.feature.tvshow.detail

import com.santimattius.kmp.entertainment.feature.shared.UiItem

data class TvShowDetailUiState(
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val data: TvShowUiModel? = null,
)

data class TvShowUiModel(
    override val id: Long,
    override val title: String,
    val image: String,
    val overview: String,
    val isFavorite: Boolean = false,
) : UiItem {
    override val imageUrl: String
        get() = image

    override val description: String
        get() = overview
}
