package com.santimattius.kmp.entertainment.core.data.entities

import com.santimattius.kmp.entertainment.core.domain.TvShow

internal fun List<TvShowDto>.asTvShows(): List<TvShow> {
    return this.map { it.asTvShow() }
}

internal fun TvShowDto.asTvShow(): TvShow {
    return TvShow(
        id = this.id,
        title = this.name,
        image = this.poster,
        overview = this.overview
    )
}