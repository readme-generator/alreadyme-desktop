package dev.yjyoon.alreadyme.data.exception

import io.ktor.client.plugins.*
import kotlinx.serialization.Serializable
import java.io.IOException

@Serializable
data class HttpException(
    override val message: String?,
    val code: Int
) : IOException(message)

fun HttpRequestTimeoutException.toHttpException() = HttpException(
    message = "HTTP Request timeout has expired.",
    code = 408
)
