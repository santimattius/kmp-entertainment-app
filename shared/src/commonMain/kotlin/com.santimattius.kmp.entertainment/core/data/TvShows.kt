package com.santimattius.kmp.entertainment.core.data

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

@Serializable
data class TvShowResponse(
    @SerialName("page") val page: Long,
    @SerialName("results") val result: List<TvShowDto>,
    @SerialName("total_pages") val totalPages: Long,
    @SerialName("total_results") val totalResults: Long,
)

@Serializable
data class TvShowDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("first_air_date") val firstAirDate: String,
    @SerialName("origin_country") val originCountry: List<String>,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("original_name") val originalName: String,
    @SerialName("overview") val overview: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Long,
) {
    val poster: String
        get() = "https://image.tmdb.org/t/p/w500${posterPath}"
}
