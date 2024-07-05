package com.santimattius.kmp.entertainment.feature.tvshow.home

import com.santimattius.kmp.entertainment.feature.shared.UiItem

data class TvShowUiModel(
    override val id: Long,
    override val title: String,
    val image: String,
) : UiItem {

    override val description: String
        get() = ""

    override val imageUrl: String
        get() = image
}
