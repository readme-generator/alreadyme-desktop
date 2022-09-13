package dev.yjyoon.alreadyme.data.repository

import dev.yjyoon.alreadyme.data.exception.CommonException
import dev.yjyoon.alreadyme.data.exception.HttpException
import dev.yjyoon.alreadyme.data.model.GitUrlRequest
import dev.yjyoon.alreadyme.data.model.IdRequest
import dev.yjyoon.alreadyme.data.model.MarkdownResponse
import dev.yjyoon.alreadyme.data.model.ReadmeResponse
import dev.yjyoon.alreadyme.data.util.FileUtil.openFileDialog
import dev.yjyoon.alreadyme.data.util.FileUtil.saveFile
import dev.yjyoon.alreadyme.ui.value.R
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class ReadmeRepository @Inject constructor(
    private val client: HttpClient
) {

    suspend fun generateReadme(url: String): Result<ReadmeResponse> = runCatching {
        val response = client.post("") {
            setBody(GitUrlRequest(url))
        }

        if (response.status != HttpStatusCode.OK) {
            throw HttpException(
                message = CommonException.message[response.status.value] ?: R.string.UNDEFINED_ERROR,
                statusCode = response.status.value
            )
        }

        return Result.success(response.body() as ReadmeResponse)
    }

    suspend fun downloadReadme(id: Long): Result<Unit> = runCatching {
        val response = client.post("download") {
            setBody(IdRequest(id))
        }

        if (response.status != HttpStatusCode.OK) {
            throw HttpException(
                message = CommonException.message[response.status.value] ?: R.string.UNDEFINED_ERROR,
                statusCode = response.status.value
            )
        }

        val responseBody: MarkdownResponse = response.body()
        val markdown: ByteArray = client.get(responseBody.objectUrl).body()

        openFileDialog { dirName, fileName -> saveFile(dirName, fileName, markdown) }
    }

    suspend fun pullRequestReadme(id: Long): Result<Unit> = runCatching {
        val response = client.post("pull-request") {
            setBody(IdRequest(id))
        }

        if (response.status != HttpStatusCode.OK) {
            throw HttpException(
                message = CommonException.message[response.status.value] ?: R.string.UNDEFINED_ERROR,
                statusCode = response.status.value
            )
        }
    }
}
