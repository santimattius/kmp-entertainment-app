package com.santimattius.kmp.entertainment.core.domain

data class Movie(
    val id: Long,
    val title: String,
    val image: String,
    val overview: String,
    val homepage: String? = null,
)
