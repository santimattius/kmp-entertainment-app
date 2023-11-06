package com.santimattius.kmp.entertainment.feature.movie.home

import com.santimattius.kmp.entertainment.core.ui.components.UiModel

data class MovieUiModel(
    override val id: Long,
    val title: String,
    val image: String,
) : UiModel
