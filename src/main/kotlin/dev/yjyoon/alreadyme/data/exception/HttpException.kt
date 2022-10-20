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
    message = "This repository is too big to analyzing!",
    code = 408
)
