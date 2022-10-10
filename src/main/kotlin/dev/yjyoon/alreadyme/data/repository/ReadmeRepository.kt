package dev.yjyoon.alreadyme.data.repository

import dev.yjyoon.alreadyme.data.exception.CommonException
import dev.yjyoon.alreadyme.data.exception.HttpException
import dev.yjyoon.alreadyme.data.model.IdRequest
import dev.yjyoon.alreadyme.data.model.MarkdownResponse
import dev.yjyoon.alreadyme.data.util.FileUtil.openFileDialog
import dev.yjyoon.alreadyme.data.util.FileUtil.saveFile
import dev.yjyoon.alreadyme.ui.value.R
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReadmeRepository @Inject constructor(
    private val client: HttpClient
) {

    suspend fun postUrl(url: String, onReceive: (String) -> Unit, onComplete: () -> Unit): Result<Unit> = runCatching {
//        val response = client.post("") {
//            setBody(GitUrlRequest(url))
//        }
//
//        if (response.status != HttpStatusCode.OK) {
//            throw HttpException(
//                message = CommonException.message[response.status.value] ?: R.string.UNDEFINED_ERROR,
//                statusCode = response.status.value
//            )
//        }

        return generateReadme(onReceive, onComplete)
    }

    private suspend fun generateReadme(onReceive: (String) -> Unit, onComplete: () -> Unit): Result<Unit> =
        runCatching {
            val selectorManager = SelectorManager(Dispatchers.IO)
            val socket = aSocket(selectorManager).tcp().connect("127.0.0.1", 9002)

            val receiveChannel = socket.openReadChannel()

            withContext(Dispatchers.IO) {
                while (true) {
                    val text = receiveChannel.readUTF8Line()

                    //TODO: Socket 프로토콜 정의
                    if (text != null) {
                        onReceive(text)
                        print(text)
                    } else {
                        socket.close()
                        selectorManager.close()
                        onComplete()
                    }
                }
            }
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
