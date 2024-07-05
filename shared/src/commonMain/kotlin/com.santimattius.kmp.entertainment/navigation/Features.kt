package com.santimattius.kmp.entertainment.navigation

enum class Features(val route: String) {
    Splash(route = "/splash"),
    Movies(route = "/movies"),
    TvShows(route = "/tv_shows"),
    Favorites(route = "/favorites")
}

fun Features.convert(): String = when (this) {
    Features.Splash -> Splash::class.qualifiedName
    Features.Movies -> Movie::class.qualifiedName
    Features.TvShows -> TvShow::class.qualifiedName
    Features.Favorites -> Favorite::class.qualifiedName
}.orEmpty()