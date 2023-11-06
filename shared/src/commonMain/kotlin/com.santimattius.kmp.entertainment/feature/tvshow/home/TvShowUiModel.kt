package com.santimattius.kmp.entertainment.feature.tvshow.home

import com.santimattius.kmp.entertainment.core.ui.components.UiModel

data class TvShowUiModel(
    override val id: Long,
    val title: String,
    val image: String,
) : UiModel
