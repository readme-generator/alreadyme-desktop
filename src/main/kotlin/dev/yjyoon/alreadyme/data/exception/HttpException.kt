package dev.yjyoon.alreadyme.data.exception

import io.ktor.client.plugins.*
import java.io.IOException

data class HttpException(
    override val message: String?,
    val statusCode: Int
) : IOException(message)

fun HttpRequestTimeoutException.toHttpException() = HttpException(
    message = "HTTP Request timeout has expired.",
    statusCode = 408
)
