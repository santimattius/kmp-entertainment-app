package com.santimattius.kmp.entertainment.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Splash

@Serializable
data object Movie

@Serializable
data class MovieDetail(val id: Long)

@Serializable
data object TvShow

@Serializable
data class TvShowDetail(val id: Long)

@Serializable
data object Favorite
