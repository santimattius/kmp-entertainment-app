package com.santimattius.kmp.entertainment.feature.movie.home

import com.santimattius.kmp.entertainment.feature.shared.UiItem

data class MovieUiModel(
    override val id: Long,
    override val title: String,
    val image: String,
) : UiItem {
    override val imageUrl: String
        get() = image

    override val description: String
        get() = ""
}
