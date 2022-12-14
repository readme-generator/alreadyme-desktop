package dev.yjyoon.alreadyme.data.di

import dagger.Module
import dagger.Provides
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideKtorHttpClient() = HttpClient(CIO) {

        // Header
        defaultRequest {
            url("http://43.200.219.154:8080/api/v1/app/")
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
        }

        // Json serializer
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true // 모델에 없고, json에 있는 경우 해당 key 무시 여부
                    prettyPrint = true
                    isLenient = true // 큰따옴표 잘못된 경우 무시하고 처리 여부
                    encodeDefaults = true // null인 값의 json 포함 여부
                }
            )
        }

        // Timeout
        install(HttpTimeout) {
            requestTimeoutMillis = 60_000L
            connectTimeoutMillis = 60_000L
        }
    }
}
