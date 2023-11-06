package com.santimattius.kmp.entertainment.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal fun ktorHttpClient(apiKey: String) = HttpClient {

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }

    defaultRequest {
        url("https://api.themoviedb.org/3")
        url {
            protocol = URLProtocol.HTTPS
            host = "api.themoviedb.org"
            path("3/")
            parameters.append("api_key", apiKey)
        }
        contentType(ContentType.Application.Json)
    }
}