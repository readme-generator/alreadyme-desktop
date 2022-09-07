package dev.yjyoon.alreadyme.data.repository

import dev.yjyoon.alreadyme.data.model.GitUrlRequest
import dev.yjyoon.alreadyme.data.model.IdRequest
import dev.yjyoon.alreadyme.data.model.MarkdownResponse
import dev.yjyoon.alreadyme.data.model.ReadmeResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import java.io.File
import javax.inject.Inject

class ReadmeRepository @Inject constructor(
    private val client: HttpClient
) {

    suspend fun generateReadme(url: String): Result<ReadmeResponse> = runCatching {
        client.post("") {
            setBody(GitUrlRequest(url))
        }.body() as ReadmeResponse
    }

    suspend fun downloadReadme(id: Long): Result<Unit> = runCatching {
        val response = client.post("download") {
            setBody(IdRequest(id))
        }.body() as MarkdownResponse
        val file = File.createTempFile("files", "index")
        val markdown: ByteArray = client.get(response.objectUrl).body()
        file.writeBytes(markdown)
        println("A file saved to ${file.path}")
    }
}
