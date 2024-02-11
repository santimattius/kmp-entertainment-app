package com.santimattius.kmp.entertainment.core.domain

data class Favorite(
    val resourceId: Long,
    val title: String,
    val overview: String,
    val imageUrl: String,
    val type: ContentType,
)

enum class ContentType {
    MOVIE, TV
}