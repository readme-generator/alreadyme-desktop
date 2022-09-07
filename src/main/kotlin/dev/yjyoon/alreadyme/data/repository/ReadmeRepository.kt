package dev.yjyoon.alreadyme.data.repository

import dev.yjyoon.alreadyme.data.model.GitUrlRequest
import dev.yjyoon.alreadyme.data.model.ReadmeResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

class ReadmeRepository @Inject constructor(
    private val client: HttpClient
) {

    suspend fun generateReadme(url: String): Result<ReadmeResponse> = runCatching {
        client.post("") {
            setBody(GitUrlRequest(url))
        }.body() as ReadmeResponse
    }
}
