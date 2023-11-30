package com.santimattius.kmp.entertainment.feature.favorites

import com.santimattius.kmp.entertainment.feature.shared.UiItem

data class FavoriteUiModel(
    override val id: Long,
    override val title: String,
    override val description: String,
    override val imageUrl: String,
) : UiItem