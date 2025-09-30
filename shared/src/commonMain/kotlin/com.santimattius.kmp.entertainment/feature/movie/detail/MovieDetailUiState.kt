package com.santimattius.kmp.entertainment.feature.movie.detail

import com.santimattius.kmp.entertainment.feature.shared.UiItem

data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val data: MovieUiModel? = null,
)


data class MovieUiModel(
    override val id: Long,
    override val title: String,
    val image: String,
    val overview: String,
    val homepage: String? = null,
    val isFavorite: Boolean = false,
) : UiItem {

    override val description: String
        get() = overview

    override val imageUrl: String
        get() = image

}