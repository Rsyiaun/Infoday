package com.example.infoday

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

object KtorClient {
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
        install(Logging)
        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
        expectSuccess = true
    }

    suspend fun getFeeds(): List<Feed> {
        return httpClient.get("https://comp4097-ass2022.herokuapp.com/event/json").body()
        //https://comp4097-ass2022.herokuapp.com/event/json
        //https://api.npoint.io/a8cea79c033ace1c8b8b
    }
    suspend fun getSigleFeeds(event: String): List<Feed> {
        return httpClient.get("https://comp4097-ass2022.herokuapp.com/event/detail/$event").body()
        //https://comp4097-ass2022.herokuapp.com/event/json
        //https://api.npoint.io/a8cea79c033ace1c8b8b
    }

    suspend fun postFeedback(feedback: String): String {
        return httpClient.post("https://httpbin.org/post") {
            setBody(feedback)
        }.body()
    }
}