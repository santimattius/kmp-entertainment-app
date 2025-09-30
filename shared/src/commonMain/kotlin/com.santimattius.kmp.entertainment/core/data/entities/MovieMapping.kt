package com.santimattius.kmp.entertainment.core.data.entities

import com.santimattius.kmp.entertainment.core.domain.Movie
import com.santimattius.kmp.entertainment.core.data.entities.Movie as MovieDto

internal fun MovieDto.asMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        image = this.poster,
        overview = this.overview,
        homepage = this.homepage
    )
}


internal fun List<MovieDto>.asMovies(): List<Movie> {
    return this.map { it.asMovie() }
}
