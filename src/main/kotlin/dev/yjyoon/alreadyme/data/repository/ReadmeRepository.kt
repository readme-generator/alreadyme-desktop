package dev.yjyoon.alreadyme.data.repository

import dev.yjyoon.alreadyme.data.exception.HttpException
import dev.yjyoon.alreadyme.data.model.GitUrlRequest
import dev.yjyoon.alreadyme.data.model.GitUrlResponse
import dev.yjyoon.alreadyme.data.model.IdRequest
import dev.yjyoon.alreadyme.data.model.MarkdownResponse
import dev.yjyoon.alreadyme.data.model.PullRequestResponse
import dev.yjyoon.alreadyme.data.util.FileUtil.openFileDialog
import dev.yjyoon.alreadyme.data.util.FileUtil.saveFile
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.websocket.*
import javax.inject.Inject

class ReadmeRepository @Inject constructor(
    private val client: HttpClient
) {

    suspend fun postUrl(
        url: String,
        onGenerate: () -> Unit,
        onReceive: (String) -> Unit,
        onComplete: (Long) -> Unit
    ): Result<Unit> = runCatching {
        val response = client.post("readme") {
            setBody(GitUrlRequest(url))
        }

        if (response.status != HttpStatusCode.OK) {
            throw HttpException(
                message = (response.body() as HttpException).message,
                code = response.status.value
            )
        }

        val responseBody: GitUrlResponse = response.body()
        val id = responseBody.id

        onGenerate()

        return generateReadme(id, onReceive) { onComplete(id) }
    }

    private suspend fun generateReadme(
        id: Long,
        onReceive: (String) -> Unit,
        onComplete: (Long) -> Unit
    ): Result<Unit> = runCatching {
        val websocketsClient = HttpClient(CIO) {
            install(WebSockets) {
                pingInterval = 20_000
            }
        }

        websocketsClient.webSocket(
            method = HttpMethod.Get,
            host = "35.223.167.76",
            port = 5558,
            path = "/api/v2/generate/stream"
        ) {
            send(id.toString())

            while (true) {
                val incoming = incoming.receive() as? Frame.Text
                val text = incoming?.readText() ?: ""

                if (text == "") {
                    onComplete(id)
                    break
                }

                onReceive(text)
            }
        }

        websocketsClient.close()
    }

    suspend fun downloadReadme(id: Long): Result<Unit> = runCatching {
        val response = client.post("download") {
            setBody(IdRequest(id))
        }

        if (response.status != HttpStatusCode.OK) {
            throw HttpException(
                message = (response.body() as HttpException).message,
                code = response.status.value
            )
        }

        val responseBody: MarkdownResponse = response.body()
        val markdown: ByteArray = client.get(responseBody.objectUrl).body()

        openFileDialog { dirName, fileName -> saveFile(dirName, fileName, markdown) }
    }

    suspend fun pullRequestReadme(id: Long): Result<String> = runCatching {
        val response = client.post("pull-request") {
            setBody(IdRequest(id))
        }

        if (response.status != HttpStatusCode.OK) {
            throw HttpException(
                message = (response.body() as HttpException).message,
                code = response.status.value
            )
        }

        val responseBody: PullRequestResponse = response.body()
        responseBody.pullRequestUrl
    }
}
