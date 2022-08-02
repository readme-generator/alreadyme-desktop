package dev.yjyoon.alreadyme.data.repository

import dev.yjyoon.alreadyme.data.model.ReadmeResponse
import dev.yjyoon.alreadyme.data.model.UrlRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay
import javax.inject.Inject

class ReadmeRepository @Inject constructor(
    private val client: HttpClient
) {

    suspend fun generateReadme(url: String): ReadmeResponse =
        client.post("url") {
            setBody(UrlRequest(url))
        }.body() as ReadmeResponse

    suspend fun test(): Result<Unit> = runCatching {
        delay(2000L)
    }
}
